package Api;

public class Time
{
    private String updatedISO;

    private String updated;

    private String updateduk;

    public String getUpdatedISO ()
    {
        return updatedISO;
    }

    public void setUpdatedISO (String updatedISO)
    {
        this.updatedISO = updatedISO;
    }

    public String getUpdated ()
    {
        return updated;
    }

    public void setUpdated (String updated)
    {
        this.updated = updated;
    }

    public String getUpdateduk ()
    {
        return updateduk;
    }

    public void setUpdateduk (String updateduk)
    {
        this.updateduk = updateduk;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updatedISO = "+updatedISO+", updated = "+updated+", updateduk = "+updateduk+"]";
    }
}

