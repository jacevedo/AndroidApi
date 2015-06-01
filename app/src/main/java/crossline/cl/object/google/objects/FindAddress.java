package crossline.cl.object.google.objects;

/**
 * Created by jacevedo on 02-01-15.
 */
public class FindAddress
{
    private Results [] results;
    private String status;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Results [] getResults()
    {
        return results;
    }

    public void setResults(Results [] results)
    {
        this.results = results;
    }
}
