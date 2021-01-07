package controller;

import controller.commands.History;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvasBase canvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasBase canvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.canvas = canvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.REDO, () -> History.redo());
        uiModule.addEvent(EventName.UNDO, () -> History.undo());
        uiModule.addEvent(EventName.COPY, () -> CommandManager.copyShape(applicationState));
        uiModule.addEvent(EventName.PASTE, () -> CommandManager.paste(canvas, applicationState));
    }
}
