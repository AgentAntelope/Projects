

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.data.Semantics;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;

public class AttributePlugin extends CytoscapePlugin {

	/**
	 * This plugin will add a menu item "Attribut Plugin" under menu "plugins"
	 * If a network exist, it will add an attribute for a node.
	 * After attribute is added, it can be find at the attribute browser
	 */
	public AttributePlugin() {
	
		// Create an Action, add the action to Cytoscape menu
	
		MyPluginAction action = new MyPluginAction(this);
		Cytoscape.getDesktop().getCyMenus().addCytoscapeAction((CytoscapeAction) action);
	}
	
	
	public class MyPluginAction extends CytoscapeAction {

		public MyPluginAction(AttributePlugin myPlugin) {
			// Add the menu item under menu pulldown "Plugins"
			super("Attribute Plugin");
			setPreferredMenu("Plugins");
		}

		public void actionPerformed(ActionEvent e) {
			
			// If there is no network, give a message to user

			CyNetwork cyNetwork = Cytoscape.createNetwork("network1", true);
			String attributeName = "Cell Part";
			ProteinFinder2 finder = new ProteinFinder2();
			finder.findEllements();

			for(int i = 0; i < finder.proteins.size(); i++)
			{
	
			CyNode node = Cytoscape.getCyNode( finder.proteins.get(i).protnum , true);
			if(finder.proteins.get(i).go_id.size()!= 0)
			{
			cyNetwork.addNode(node);
			CyAttributes cyNodeAttrs = Cytoscape.getNodeAttributes();
			//Grab the attribute for this specific node and then set it in Cytoscape
			cyNodeAttrs.setAttribute(node.getIdentifier(), attributeName, finder.proteins.get(i).go_id.get(0));
			}
			}
			JOptionPane.showMessageDialog(Cytoscape.getDesktop(),"Done" );

			finder.setEdges();
			for(int i = 0; i< finder.edgeleft.size(); i++)
			{
				CyNode node0 = Cytoscape.getCyNode(finder.edgeleft.get(i));
				CyNode node1 = Cytoscape.getCyNode(finder.edgeright.get(i));
				
				if(node1 == null || node0 == null || node1 == node0)
				{
	
				}
				else
				{
					CyEdge edge0 = Cytoscape.getCyEdge(node0, node1, Semantics.INTERACTION, "pp", true);
					cyNetwork.addEdge(edge0);
				}
			}

			// Inform Cytoscape via property change event.
			Cytoscape.firePropertyChange(Cytoscape.ATTRIBUTES_CHANGED, null, null);
		}

		public boolean isInToolBar() {
			return false;
		}


		public boolean isInMenuBar() {
			return true;
		}
	}
}
