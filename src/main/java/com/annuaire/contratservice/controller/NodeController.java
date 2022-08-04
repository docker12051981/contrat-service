package com.annuaire.contratservice.controller;
import com.annuaire.contratservice.object.ContratNode;
import com.annuaire.contratservice.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author karim hmadi

 * @description recursiv contrat controllor

 */
@CrossOrigin(origins = "*")
@RestController
public class NodeController {
    @Autowired
    NodeService nodeService;
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/contrats/node/{treeId}")
    public ResponseEntity<ContratNode> getFullTree(@PathVariable("treeId") int treeId) {
        return ResponseEntity.ok(nodeService.getFullTree(treeId));
    }
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/contrats/node/{treeId}/st/{nodeId}")
    public ResponseEntity<ContratNode> getSubtree(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId) {
        return ResponseEntity.ok(nodeService.getSubTree(treeId, nodeId, null));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/contrats/node/{treeId}/{nodeId}")
    public ResponseEntity<Void> deleteNodes(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId) {
        nodeService.deleteNodes(treeId, nodeId);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/contrats/node/")
    public ResponseEntity<Void> create(@RequestBody ContratNode treeNode) {
        nodeService.create(treeNode);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/contrats/node/{treeId}/{nodeId}")
    public ResponseEntity<Void> move(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId, @RequestParam int newParentNodeId) {
        nodeService.move(treeId, nodeId , newParentNodeId);
        return ResponseEntity.ok().build();
    }

}
