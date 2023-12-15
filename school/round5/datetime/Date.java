class Date {
    private Integer year;
    private Integer month;
    private Integer day;

    public Date(Integer year, Integer month, Integer day) 
        throws DateException {
        if (year > 0 && month > 0 && month <= 12 && day>0 && day <= 31) {
            if (month.equals(11) && day.equals(31)) {
                throw new DateException("Illegal date " + toString(year,month,day));
            }
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            throw new DateException("Illegal date " + toString(year,month,day));
        }
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String toString() {
        String dtStr = "";
        if (this.day < 10) {
            dtStr += "0"+this.day;
        } else {
            dtStr += this.day;
        }
        dtStr+=".";
        if (this.month < 10) {
            dtStr += "0"+this.month;
        } else {
            dtStr += this.month;
        }
        dtStr+=".";
        dtStr += this.year;

        return  dtStr;
    }

    public String toString(Integer year, Integer month, Integer day) {
        String dtStr = "";
        if (day < 10) {
            dtStr += "0"+day;
        } else {
            dtStr += day;
        }
        dtStr+=".";
        if (month < 10) {
            dtStr += "0"+month;
        } else {
            dtStr += month;
        }
        dtStr+=".";
        dtStr += year;

        return  dtStr;
    }
}