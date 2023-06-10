package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;

public class MusicItem extends Item{
    private MusicGenre musicGenre;
    private boolean hasAttachedVideo;

    public MusicItem(String name, Category category, int price, int quantity, MusicGenre musicGenre, boolean hasAttachedVideo) {
        super(name, category, price, quantity);
        this.musicGenre = musicGenre;
        this.hasAttachedVideo = hasAttachedVideo;
    }

    public MusicItem() {
        super(Category.MUSIC);
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Gatunek", musicGenre);
        result.put("Dołączone wideo", hasAttachedVideo);

        return result;
    }

    @Override
    public boolean satisfiesFilterExtraProperties(Item item) {
        if (!(item instanceof MusicItem referenceItem)) {
            return false;
        }

        return !(referenceItem.hasAttachedVideo && !this.hasAttachedVideo);
    }

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

    public boolean isHasAttachedVideo() {
        return hasAttachedVideo;
    }

    public void setHasAttachedVideo(boolean hasAttachedVideo) {
        this.hasAttachedVideo = hasAttachedVideo;
    }
}
