import java.util.Comparator;

/**
 * Created by Vincent Brands on 6/1/2016.
 */
public class ClientJoinedComparator implements Comparator<ClientThread> {


    @Override
    public int compare(ClientThread o1, ClientThread o2) {
        if(o1.getDate().before(o2.getDate()))
            return -1;
        else if (o1.getDate().after(o2.getDate()))
            return 1;
        else return 0;
    }




}
