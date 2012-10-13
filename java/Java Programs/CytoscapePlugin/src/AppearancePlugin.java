

import java.awt.Color;
import java.awt.event.ActionEvent;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.GlobalAppearanceCalculator;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualPropertyType;
import cytoscape.visual.VisualStyle;
import cytoscape.visual.calculators.BasicCalculator;
import cytoscape.visual.calculators.Calculator;
import cytoscape.visual.mappings.DiscreteMapping;
import cytoscape.visual.mappings.ObjectMapping;

/**
 * 
 */
public class AppearancePlugin extends CytoscapePlugin {

	/**
	 * create a menu item
	 */
	public AppearancePlugin() {
		// Create an Action, add the action to Cytoscape menu
		MyPluginAction action = new MyPluginAction(this);
		Cytoscape.getDesktop().getCyMenus().addCytoscapeAction((CytoscapeAction) action);
	}

	public static final String vsName = "Example Visual Style";

	public class MyPluginAction extends CytoscapeAction {

		public MyPluginAction(AppearancePlugin myPlugin) {
			// Add the menu item under menu pulldown "Plugins"
			super("Appearance Plugin");
			setPreferredMenu("Plugins");
		}

		public void actionPerformed(ActionEvent e) {
			// get the network and view
			CyNetwork network = Cytoscape.getCurrentNetwork();
			CyNetworkView networkView = Cytoscape.getCurrentNetworkView();

			// get the VisualMappingManager and CalculatorCatalog
			VisualMappingManager manager = Cytoscape.getVisualMappingManager();
			CalculatorCatalog catalog = manager.getCalculatorCatalog();

			// check to see if a visual style with this name already exists
			VisualStyle vs = catalog.getVisualStyle(vsName);
			if (vs == null) {
				// if not, create it and add it to the catalog
				vs = createVisualStyle(network);
				catalog.addVisualStyle(vs);
			}
			
			networkView.setVisualStyle(vs.getName()); // not strictly necessary

			// actually apply the visual style
			manager.setVisualStyle(vs);
			networkView.redrawGraph(true,true);
		}

		@SuppressWarnings("deprecation")
		VisualStyle createVisualStyle(CyNetwork network) {

			NodeAppearanceCalculator nodeAppCalc = new NodeAppearanceCalculator();
			EdgeAppearanceCalculator edgeAppCalc = new EdgeAppearanceCalculator();
			GlobalAppearanceCalculator globalAppCalc = new GlobalAppearanceCalculator(); 


			
		globalAppCalc.setDefaultBackgroundColor(Color.WHITE);
		DiscreteMapping disMapping = new DiscreteMapping(NodeShape.ELLIPSE,
                ObjectMapping.NODE_MAPPING);
		Calculator shapeCalculator = new BasicCalculator("Node Shapes",disMapping, VisualPropertyType.NODE_SHAPE);
		nodeAppCalc.setCalculator(shapeCalculator);
		
		// Create the visual style 
			VisualStyle visualStyle = new VisualStyle(vsName, nodeAppCalc, edgeAppCalc, globalAppCalc);

			return visualStyle;
		}
	}

}
