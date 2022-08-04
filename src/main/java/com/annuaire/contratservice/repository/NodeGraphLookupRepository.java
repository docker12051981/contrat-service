package com.annuaire.contratservice.repository;

import com.annuaire.contratservice.model.Node;

import java.util.List;
import java.util.Optional;

/**
 * @author karim hmadi

 * @description GraphLookupRepository for extracting subtree

 */
public interface NodeGraphLookupRepository {
    Optional<List<Node>> getSubTree(int treeId, int nodeId, Long maxDepth);
}
