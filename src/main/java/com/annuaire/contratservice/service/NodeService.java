package com.annuaire.contratservice.service;
import com.annuaire.contratservice.object.ContratNode;
import org.springframework.util.CollectionUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author karim hmadi

 * @description interface service node contrat (for recursive)

 */
public interface NodeService {


    int DEFAULT_ROOT_NODE_ID = -1;

    ContratNode getFullTree(int treeId);

    ContratNode getSubTree(int treeId, int nodeId, Long maxDepth);

    void deleteNodes(int treeId, int nodeId);

    void create(ContratNode treeNode);

    void move(int treeId, int nodeId, int newParentNodeId);

    static ContratNode assembleTree(final List<ContratNode> nodes, final int rootNodeId) {
        final Map<Integer, ContratNode> mapTmp = new LinkedHashMap<>();
        // Save all nodes to a map
        for (final ContratNode current : nodes) {
            mapTmp.put(current.getKey(), current);
        }
        // Loop and assign parent/child relationships
        for (final ContratNode current : nodes) {
            final List<Integer> parents = current.getParentId();

            if (!CollectionUtils.isEmpty(parents)) {
                for (final Integer pid : parents) {
                    final ContratNode parent = mapTmp.get(pid);
                    if (parent != null) {
                        parent.addChild(current);
                        current.addParent(parent);
                    }
                }
            }
        }
        return mapTmp.get(rootNodeId);
    }

}
