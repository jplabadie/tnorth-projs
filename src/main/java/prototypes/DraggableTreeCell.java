package prototypes;

import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 */
class DraggableTreeCell<T> extends TreeCell<T> {
    private String name = "";

    public DraggableTreeCell() {
        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                // Store node ID in order to know what is dragged.
                content.putString(event.getSource().toString());
                db.setContent(content);
                event.consume();
            }
        });

    }
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        try {
            this.setText(this.getTreeItem().getValue().toString());
            name = this.getText();
        }
        catch (NullPointerException e){
        }

    }


}
