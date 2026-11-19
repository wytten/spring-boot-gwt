package fr.ekito.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.ekito.gwt.client.controller.WebAppController;
import fr.ekito.gwt.client.model.ModelHandler;
import fr.ekito.gwt.client.resource.ApplicationResources;
import fr.ekito.gwt.client.ui.MainPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * Point d'entrée du module GWT
 */
public class GwtWebApp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// ensure resources are injected
		ApplicationResources.INSTANCE.style().ensureInjected();

		// wire the application's singletons by hand
		SimpleEventBus eventBus = new SimpleEventBus();
		ModelHandler modelHandler = new ModelHandler();
		MainPanel mainPanel = new MainPanel(eventBus, modelHandler);
		WebAppController controller = new WebAppController(eventBus, modelHandler, mainPanel);

		// bind event handlers
		controller.bindHandlers();
		// add for display
		RootLayoutPanel.get().add(mainPanel);
	}
}
