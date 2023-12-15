class Course 
{
    private String code;
    private String name;
    private int credits;

    public  Course(String code, String name, int credits)
    {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public  String getCode()
    {
        return this.code;
    }    

    public  String getName()
    {
        return this.name;
    }

    public int getCredits()
    {
        return this.credits;
    }
}