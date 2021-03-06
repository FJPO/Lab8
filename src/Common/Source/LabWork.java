package Common.Source;


import Common.Security.User;
import Common.OBSOLETE_FileInteraction.XmlTools;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LabWork implements Xml_Interchangeable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private int tunedInWorks;
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null

    private User creator;

    public LabWork(){}

    public LabWork(String name, Coordinates coordinates, Double minimalPoint, int turnedInWorks, Double averagePoint,
                   Difficulty difficulty, Person author, User creator) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("labName error");
        } else if (coordinates == null) {
            throw new IllegalArgumentException();
        } else if (minimalPoint == null) {
            throw new IllegalArgumentException();
        } else if (author == null) {
            throw new IllegalArgumentException();
        } else if (minimalPoint < 0) {
            throw new IllegalArgumentException();
        }
        if (averagePoint != null)
            if(averagePoint < 0)
                throw new IllegalArgumentException();

        id = -1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = turnedInWorks;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
        this.creator = creator;
    }

    public LabWork(int id, String name, Coordinates coordinates, LocalDateTime creationDate,
                   Double minimalPoint, int turnedInWorks, Double averagePoint,
                   Difficulty difficulty, Person author, User creator) {

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = turnedInWorks;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
        this.creator = creator;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public float getXCoordinate(){
        return coordinates.getX();
    }
    public int getYCoordinate(){
        return coordinates.getY();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public int getTunedInWorks() {
        return tunedInWorks;
    }

    public Double getAveragePoint() {
        return averagePoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public String getStringDifficulty(){return (difficulty==null)?"":difficulty.name();}

    public Person getAuthor() {
        return author;
    }
    public String getAuthorName(){return author.getName();}
    public Long getAuthorHeight(){return author.getHeight();}
    public Float getAuthorX(){return author.getLocation().getX();}
    public int getAuthorY(){return author.getLocation().getY();}
    public Long getAuthorZ(){return author.getLocation().getZ();}

    public String getCreatorName(){return creator.getLogin();}


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void setAveragePoint(Double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTunedInWorks(int tunedInWorks) {
        this.tunedInWorks = tunedInWorks;
    }

    @Override
    public String toString() {
        return "\n***\nЛабораторная работа:\n\n" + "id: " + id + "\nНазване: " + name + "\n" + coordinates +
                "\nВремя создания: " + creationDate
                + "\nПроходной балл: " + minimalPoint + "\nЕще какой-то параметер: " + tunedInWorks +
                "\nСредний Балл: " + averagePoint + "\n" + difficulty + "\n" + author + "\nСоздатель: " + creator + "\n*****";
    }

    @Override
    public String createXml() {

        String line = "<LabWork>\n" + XmlTools.createXmlLine("id", String.valueOf(id)) +
                XmlTools.createXmlLine("name", name) +
                coordinates.createXml() +

                XmlTools.createXmlLine("creationDate", creationDate.toString()) +

                XmlTools.createXmlLine("minimalPoint", String.valueOf(minimalPoint)) +
                XmlTools.createXmlLine("tunedInWorks", String.valueOf(tunedInWorks)) +
                XmlTools.createXmlLine("averagePoint", String.valueOf(averagePoint)) +
                difficulty.createXml() +
                author.createXml() +
                "</LabWork>\n";
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LabWork)) return false;
        LabWork tempo = (LabWork) obj;
        return this.getId()==tempo.getId() && this.getName().equals(tempo.getName()) && this.getXCoordinate()==tempo.getXCoordinate() &&
                this.getYCoordinate()==tempo.getYCoordinate() && this.getMinimalPoint().equals(tempo.getMinimalPoint()) &&
                this.getTunedInWorks()==tempo.getTunedInWorks() && this.getAveragePoint().equals(tempo.averagePoint) &&
                this.getDifficulty() == tempo.getDifficulty() && this.getAuthorName().equals(tempo.getAuthorName());

    }

    public User getCreator() {
        return creator;
    }
}
