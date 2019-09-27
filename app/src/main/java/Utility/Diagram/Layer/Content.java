package Utility.Diagram.Layer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Utility.Bases.SuperContent;
import Utility.Diagram.BaseFeed;
import Utility.Diagram.Type;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.Diagram.BaseFeed> diagramUnitFeeds = new HashMap<>();
    private final HashMap<String, Type> diagramUnitType = new HashMap<>();

    public BaseFeed getDiagramUnitFeed(String diagramKey) {
        validateDiagramKeyExists(diagramKey);
        return diagramUnitFeeds.get(diagramKey);
    }

    public void addDiagramUnitFeed(String diagramName, Type diagramType, Utility.Diagram.BaseFeed diagramFeed) {
        validateNoDiagramKeyExists(diagramName);

        this.diagramUnitFeeds.put(diagramName, diagramFeed);
        this.diagramUnitType.put(diagramName, diagramType);

        this.addUnitFeed(diagramFeed);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.diagram_added, diagramType, diagramName);
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void removeDiagramUnitFeed(String diagramName) {
        validateDiagramKeyExists(diagramName);

        this.removeUnit(this.diagramUnitFeeds.remove(diagramName));
        Type diagramType = this.diagramUnitType.remove(diagramName);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.diagram_removed, diagramType, diagramName);
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public Utility.Diagram.BaseFeed getUnitFeed(String diagramKey) {
        validateDiagramKeyExists(diagramKey);
        return this.diagramUnitFeeds.get(diagramKey);
    }

    public Set<String> getDiagramKeys() {
        return new HashSet<>(this.diagramUnitFeeds.keySet());
    }

    public void clear() {
        for (String diagramKey : getDiagramKeys()) {
            removeDiagramUnitFeed(diagramKey);
        }
    }

    @Override
    public void refreshIntent() {
        for (Utility.Diagram.BaseFeed unitFeed : diagramUnitFeeds.values()) {
            if (unitFeed.getContent() != null) {
                unitFeed.getContent().refreshIntent();
            }
            alertComposingGroupOfChangeToConstituent();
        }

        super.refreshIntent();
    }

    private void validateDiagramKeyExists(String key) {
        if (!diagramUnitFeeds.keySet().contains(key)) {
            throw new IllegalArgumentException("No Diagram.BaseFeed object with key " + key + " exists.");
        }
    }

    private void validateNoDiagramKeyExists(String key) {
        if (diagramUnitFeeds.keySet().contains(key)) {
            throw new IllegalArgumentException("A Diagram.BaseFeed object with key " + key + " already exists.");
        }
    }
}
