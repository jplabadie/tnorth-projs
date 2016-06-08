package ctrls;

/**
 * @author Jean-Paul Labadie
 */

    import javafx.event.EventHandler;
    import javafx.scene.Cursor;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.Region;

    /**
     * {@link DragResizerController} can be used to add mouse listeners to a {@link Region}
     * and make it resizable by the user by clicking and dragging the border in the
     * same way as a window.
     * <p>
     * Only height resizing is currently implemented. Usage: <pre>DragResizer.makeResizable(myAnchorPane);</pre>
     *
     * @author atill
     *
     */
    public class DragResizerController {

        /**
         * The margin around the control that a user can click in to main resizing
         * the region.
         */
        private static final int RESIZE_MARGIN = 5;

        private final Region region;

        private double x_axis;

        private boolean initMinWidth;

        private boolean dragging;

        /*Constructor that creates and instantiates 
        the region that we will be clicked and dragged for resizing.*/
        private DragResizerController(Region aRegion) {
            region = aRegion;
        }

        /*
        Invoked after we validate that a zone is draggable and 
        then creates an instance listening to the mouse drags 
        to make the new size of the region.
        */
        public static void makeResizable(Region region) {
            final DragResizerController resizer = new DragResizerController(region);

            region.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mousePressed(event);
                }});
            region.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseDragged(event);
                }});
            region.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseOver(event);
                }});
            region.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseReleased(event);
                }});
        }

        /*
        Resets the cursor to the default state when released. 
        */
        protected void mouseReleased(MouseEvent event) {
            dragging = false;
            region.setCursor(Cursor.DEFAULT);
        }

        /*
        Checks to see if we are dragging in a draggable zone and 
        then sets the cursor to the new resize value.
        */
        protected void mouseOver(MouseEvent event) {
            if(isInDraggableZone(event) || dragging) {
                region.setCursor(Cursor.S_RESIZE);
            }
            else {
                region.setCursor(Cursor.DEFAULT);
            }
        }

        /*
        Called on a mouse event, and then checks to see if that mouse click is 
        inside a valid zone. It essentially checks to see if the thing that 
        is trying to be dragged is in fact draggable. 
        */
        protected boolean isInDraggableZone(MouseEvent event) {
            return event.getX() > (region.getWidth() - RESIZE_MARGIN);
        }

        /*
        Listens for a mouse event and returns a x_axis variable 
        to which the new window size will be adjusted to.
        */
        protected void mouseDragged(MouseEvent event) {
            if(!dragging) {
                return;
            }

            double mouse_x_delta = event.getX();

            double newWidth = region.getMinWidth() + (mouse_x_delta - x_axis);

            region.setMinWidth(newWidth);

            x_axis = mouse_x_delta;
        }

        /*
        Responsible for ignoring clicks outside of a draggable event.
        */
        protected void mousePressed(MouseEvent event) {

            // ignore clicks outside of the draggable margin
            if(!isInDraggableZone(event)) {
                return;
            }

            dragging = true;

            // make sure that the minimum height is set to the current height once,
            // setting a min height that is smaller than the current height will
            // have no effect
            if (!initMinWidth) {
                region.setMinWidth(region.getWidth());
                initMinWidth = true;
            }

            x_axis = event.getX();
        }
    }

