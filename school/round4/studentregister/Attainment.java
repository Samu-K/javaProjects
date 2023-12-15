class Attainment 
{
    private String code;
    private String studnum;
    private int grade;

    public Attainment(String courseCode, String studentnumber, int grade)
    {
        this.code = courseCode;
        this.studnum = studentnumber;
        this.grade = grade;

    }

    public String getCourseCode()
    {
        return this.code;
    }

    public String getStudentNumber()
    {
        return this.studnum;
    }
    
    public int getGrade()
    {
        return this.grade;
    }

}