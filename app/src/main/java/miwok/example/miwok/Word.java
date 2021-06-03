package miwok.example.miwok;

public class Word {
    private String TeluguTranslation;
    private String EnglishTranslation;
    private int ImageResourceId = NO_IMAGE_PROVIDED;
    private int AudioResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String telugu, String english,int audioResourceId)
    {
        TeluguTranslation = telugu;
        EnglishTranslation = english;
        AudioResourceId = audioResourceId;
    }
    public Word(String telugu, String english, int ImageResId, int audioResourceId)
    {
        TeluguTranslation = telugu;
        EnglishTranslation = english;
        ImageResourceId = ImageResId;
        AudioResourceId = audioResourceId;
    }

    public String getTeluguTranslation()
    {
        return TeluguTranslation;
    }
    public String getEnglishTranslation()
    {
        return  EnglishTranslation;
    }
    public int getImageResourceId()
    {
        return ImageResourceId;
    }
    public int getAudioResourceId() { return AudioResourceId; }


    public boolean hasImage()
    {
        return ImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "TeluguTranslation='" + TeluguTranslation + '\'' +
                ", EnglishTranslation='" + EnglishTranslation + '\'' +
                ", ImageResourceId=" + ImageResourceId +
                ", AudioResourceId=" + AudioResourceId +
                '}';
    }
}
