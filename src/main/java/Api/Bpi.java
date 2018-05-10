package Api;

public class Bpi
{
    private EUR EUR;

    private USD USD;

    private GBP GBP;

    public EUR getEUR ()
    {
        return EUR;
    }

    public void setEUR (EUR EUR)
    {
        this.EUR = EUR;
    }

    public USD getUSD ()
    {
        return USD;
    }

    public void setUSD (USD USD)
    {
        this.USD = USD;
    }

    public GBP getGBP ()
    {
        return GBP;
    }

    public void setGBP (GBP GBP)
    {
        this.GBP = GBP;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [EUR = "+EUR+", USD = "+USD+", GBP = "+GBP+"]";
    }
}

