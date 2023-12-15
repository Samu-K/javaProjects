class DateTime extends Date{
    private int hour;
    private int minute; 
    private int second;

    public DateTime(int year, int month, int day, int hour, int minute, int second) 
        throws DateException{
            super(year,month,day);
            
            if (isValid(hour,  minute,  second)) {
                this.hour = hour;
                this.minute = minute;
                this.second = second;
            } else {
                throw new DateException("Illegal time " + hour+":"+minute+":"+second);
            }
    }

    public int getHour() {
        return this.hour;
    }
    public int getMinute() {
        return this.minute;
    }
    public int getSecond() {
        return this.second;
    }

    private boolean isValid(int hour, int minute, int second) {
        if (hour < 0 || minute<0 || second<0) {
            return false;
        } else if (hour > 23 || minute > 59 || second > 59) {
            return false;
        }
        return true;
    }

    public String toString() {
        String dateStr = super.toString(this.getYear(),this.getMonth(),this.getDay());
        dateStr += " ";

        if (this.hour < 10) {
            dateStr += "0";
        }
        dateStr += this.hour+":";

        if (this.minute < 10) {
            dateStr += "0";
        }

        dateStr += this.minute+":";
        if (this.second < 10) {
            dateStr += "0";
        }
        dateStr += this.second;
        return dateStr;
    }
}