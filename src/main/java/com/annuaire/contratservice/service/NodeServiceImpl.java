package com.annuaire.contratservice.service;

import com.annuaire.contratservice.controller.FournisseurController;
import com.annuaire.contratservice.exception.NotFoundException;
import com.annuaire.contratservice.model.Node;
import com.annuaire.contratservice.object.ContratNode;
import com.annuaire.contratservice.repository.NodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author karim hmadi

 * @description implement service node structure for recursivity

 */
@Service
public class NodeServiceImpl implements NodeService{

    private static final Logger logger = LoggerFactory.getLogger(FournisseurController.class);
    private final NodeRepository nodeRepository;
    public NodeServiceImpl(NodeRepository  nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
    @Override
    public ContratNode getFullTree(int treeId) {
        List<Node> nodes = nodeRepository.findDistinctByTreeId(treeId).orElseThrow(NotFoundException::new);
        /*List<Node> nodes = nodeRepository.findByTreeId(treeId).orElseThrow(NotFoundException::new);*/
        List<ContratNode> contratNodes = new ArrayList<>();
        for (Node node : nodes) {
            ContratNode contratNode = new ContratNode();
            BeanUtils.copyProperties(node, contratNode, "children");
            contratNodes.add(contratNode);
        }
        return NodeService.assembleTree(contratNodes, NodeService.DEFAULT_ROOT_NODE_ID);
    }

    @Override
    @Transactional(readOnly = true)
    public ContratNode getSubTree(int treeId, int nodeId, Long maxDepth) {
        List<Node> nodes = nodeRepository.getSubTree(treeId, nodeId, null).orElseThrow(NotFoundException::new);

        List<ContratNode> flatList = nodes.stream()
                .map(Node::getDescendants)
                .flatMap(Collection::stream)
                .map(node -> {
                    ContratNode tr = new ContratNode();
                    BeanUtils.copyProperties(node, tr);
                    return tr;
                })
                .collect(Collectors.toList());

        ContratNode root = new ContratNode();
        BeanUtils.copyProperties(nodes.get(0), root, "id", "children");
        flatList.add(root);
        return (NodeService.assembleTree(flatList, nodeId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNodes(int treeId, int nodeId)  {
        // ... perform validations etc.
        List<Node> nodes = nodeRepository.getSubTree(treeId, nodeId, 1L).orElseThrow(NotFoundException::new);
        var target = nodes.get(0);
        if (!CollectionUtils.isEmpty(target.getDescendants())) {
            target.getDescendants().forEach(n -> n.setParentId(target.getParentId()));
            nodeRepository.saveAll(target.getDescendants());
        }
        nodeRepository.delete(target);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ContratNode contratNode) {
        // ... check if parent exists etc.
        Node node = new Node();

        node.setTreeId(contratNode.getTreeId());
        node.setStructureId(contratNode.getStructureId());
        node.setOrgId(contratNode.getOrgId());
        node.setParentId(contratNode.getParentId());
        node.setReference(contratNode.getReference());
        node.setObjet(contratNode.getObjet());
        node.setStatus(contratNode.getStatus());
        node.setEtat(contratNode.getEtat());
        node.setTypecontrat(contratNode.getTypecontrat());
        node.setNaturecontrat(contratNode.getNaturecontrat());
        node.setDate_debut(contratNode.getDate_debut());
        node.setDate_fin(contratNode.getDate_fin());
        node.setDuree_annuel(contratNode.getDuree_annuel());
        node.setEcheance(contratNode.getEcheance());
        node.setIsnode(contratNode.isIsnode());
        node.setStatus(contratNode.getStatus());
        node.setCreatedBy(contratNode.getCreatedBy());
        node.setKey(new Random().nextInt()); // set a unique nodeId based on your policy
        if(contratNode.getNodeId() == 0)
        {
            node.setNodeId(node.getKey());
        }
        else if (contratNode.getNodeId()!=0)
        {
            node.setNodeId(contratNode.getNodeId());
        }
        logger.info("Methode createnode(): before : create : {} ", node.getKey());
        logger.info("Methode createnode(): after : create : {} ", contratNode.getTreeId());
        nodeRepository.save(node);
        // iterate children and persist them as well...
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void move(int treeId, int nodeId, int newParentNodeId) {
        // ... perform validations etc.
        var node = nodeRepository.findDistinctByTreeIdAndKey(treeId, nodeId).orElseThrow(NotFoundException::new);
        node.setParentId(List.of(newParentNodeId));
        nodeRepository.save(node);
    }


}
