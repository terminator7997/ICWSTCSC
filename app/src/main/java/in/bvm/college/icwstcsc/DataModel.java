package in.bvm.college.icwstcsc;

public class DataModel {

    String name;
    String title;
    String description;
    int id_;
    int image;

    public DataModel(String title,String name, String description, int id_, int image) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.id_ = id_;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}