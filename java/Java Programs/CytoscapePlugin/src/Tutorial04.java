

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;

/**
 * 
 */
public class Tutorial04 extends CytoscapePlugin {

	/**
	 * 
	 */
	public Tutorial04() {
		// Create an Action, add the action to Cytoscape menu
		MyPluginAction action = new MyPluginAction(this);
		Cytoscape.getDesktop().getCyMenus().addCytoscapeAction((CytoscapeAction) action);
	}
	
	public class MyPluginAction extends CytoscapeAction {

		public MyPluginAction(Tutorial04 myPlugin) {
			// Add the menu item under menu pulldown "Plugins"
			super("Node Creation");
			setPreferredMenu("Plugins");
		}

		public void actionPerformed(ActionEvent e) {
			String attributeName = "Cell Part";

			//create a network without a view
			CyNetwork cyNetwork = Cytoscape.createNetwork("network1", true);
			List pew = new ArrayList();
			pew.add("Hello");
			CyNode node0 = Cytoscape.getCyNode("rain", true);
			CyAttributes cyNodeAttrs = Cytoscape.getNodeAttributes();
			cyNodeAttrs.setAttribute(node0.getIdentifier(), attributeName, "Hello");

			cyNetwork.addNode(node0);

		
			// remove a node
			//cyNetwork.removeNode(node1.getRootGraphIndex(), true);
			//Cytoscape.firePropertyChange(Cytoscape.NETWORK_MODIFIED, null, cyNetwork);
			
			// destroy the network
			//Cytoscape.destroyNetwork(cyNetwork);
			//Cytoscape.firePropertyChange(Cytoscape.NETWORK_DESTROYED, cyNetwork, null);
		}
	}
}
