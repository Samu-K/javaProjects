import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* 
public class StudentRegister {
    public static void main(String[] args) {
        StudentRegisterClass studreg = new StudentRegisterClass();
        Student example = new Student("Testeri", "150674");
        Course exCourse = new Course("123","testCourse",2);
        Course exCourse2 = new Course("1234", "Test2", 2);
        Attainment exAtt = new Attainment("123", "150674", 3);
        Attainment exAtt2 = new Attainment("1234", "150674", 4);

        studreg.addStudent(example);
        studreg.addCourse(exCourse);
        studreg.addCourse(exCourse2);
        studreg.addAttainmnet(exAtt);
        studreg.addAttainmnet(exAtt2);

        studreg.printStudentAttainments("150674", "by code");
    }
}
*/

class StudentNameComparator implements Comparator<Student>
{
    public int compare(Student stud1, Student stud2)
    {
        return stud1.getName().compareTo(stud2.getName());
    }
}

class CourseNameComparator implements Comparator<Course>
{
    public int compare(Course c1, Course c2)
    {
        return c1.getName().compareTo(c2.getName());
    }
}

class CourseCodeComparator implements Comparator<Course>
{
    public int compare(Course c1, Course c2)
    {
        return  c1.getCode().compareTo(c2.getCode());
    }
}

class StudentRegister
{
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Attainment> grades = new ArrayList<>();

    public  StudentRegister()
    {
    }

    public ArrayList<Student> getStudents()
    {
        Collections.sort(this.students, new StudentNameComparator());
        return this.students;
    }

    public ArrayList<Course> getCourses()
    {
        Collections.sort(this.courses, new CourseNameComparator());
        return this.courses;
    }

    public void printStudentAttainments(String studentNumber, String order) 
    {
        String studName = "";
        for (Student val : this.students) {
            if (val.getStudentNumber().equals(studentNumber)) {
                studName = val.getName();
            }
        }
        if (studName.equals("")) {
            System.out.println("Unknown student number: " + studentNumber);
            return;
        }
        
        System.out.println(studName + " (" + studentNumber+"):");

        ArrayList<Attainment> studentAtts = new ArrayList<>();
        for (Attainment att : this.grades) {
            if (att.getStudentNumber().equalsIgnoreCase(studentNumber)) {
                //System.out.println("Added att" + att.getCourseCode());
                studentAtts.add(att);
            }
        }
        ArrayList<Course> attCourses = new ArrayList<>();
        for (Attainment att : studentAtts) {
            String code = att.getCourseCode();
            for (Course cor : this.courses) {
                if (code.equals(cor.getCode())) {
                    //System.out.println("Added course" + cor.getCode());
                    attCourses.add(cor);
                }
            }
        }

        if (order.equals("by name")) {
            Collections.sort(attCourses,new CourseNameComparator());
            for (Course cor : attCourses) {
                Integer grade=0;
                for (Attainment att : studentAtts) {
                    if (att.getCourseCode().equals(cor.getCode())) {
                        grade = att.getGrade();
                        break;
                    }
                }
                // code Name: grade
                System.out.println("  "+cor.getCode() + " " + cor.getName() + ": " + grade);
            }
        } else if (order.equals("by code")) {
            Collections.sort(attCourses,new CourseCodeComparator());
            for (Course cor : attCourses) {
                Integer grade=0;
                for (Attainment att : studentAtts) {
                    if (att.getCourseCode().equals(cor.getCode())) {
                        grade = att.getGrade();
                        break;
                    }
                }
                // code Name: grade
                System.out.println("  "+cor.getCode() + " " + cor.getName() + ": " + grade);
            }
        } else {
            for (Course cor : attCourses) {
                Integer grade=0;
                for (Attainment att : studentAtts) {
                    if (att.getCourseCode().equals(cor.getCode())) {
                        grade = att.getGrade();
                        break;
                    }
                }
                // code Name: grade
                System.out.println("  "+cor.getCode() + " " + cor.getName() + ": " + grade);
            }
        }
    }

    public void printStudentAttainments(String studentNumber)
    {
        String studName = "";
        for (Student val : this.students) {
            if (val.getStudentNumber().equals(studentNumber)) {
                studName = val.getName();
            }
        }
        if (studName.equals("")) {
            System.out.println("Unknown student number: " + studentNumber);
            return;
        }
        System.out.println(studName + " (" + studentNumber+"):");

        ArrayList<Attainment> studentAtts = new ArrayList<>();
        for (Attainment att : this.grades) {
            if (att.getStudentNumber().equalsIgnoreCase(studentNumber)) {
                //System.out.println("Added att" + att.getCourseCode());
                studentAtts.add(att);
            }
        }
        ArrayList<Course> attCourses = new ArrayList<>();
        for (Attainment att : studentAtts) {
            String code = att.getCourseCode();
            for (Course cor : this.courses) {
                if (code.equals(cor.getCode())) {
                    //System.out.println("Added course" + cor.getCode());
                    attCourses.add(cor);
                }
            }
        }
        for (Course cor : attCourses) {
            Integer grade=0;
            for (Attainment att : studentAtts) {
                if (att.getCourseCode().equals(cor.getCode())) {
                    grade = att.getGrade();
                    break;
                }
            }
            // code Name: grade
            System.out.println("  "+cor.getCode() + " " + cor.getName() + ": " + grade);
        }
    }

    public void addStudent(Student student)
    {
        this.students.add(student);
    }

    public void addCourse(Course course) 
    {
        this.courses.add(course);
    }

    public void addAttainment(Attainment att) 
    {
        this.grades.add(att);
    }
}