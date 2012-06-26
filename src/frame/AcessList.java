package frame;
import util.List;

public class AcessList extends List<Access> {

	public Access head;
	public AcessList tail;
	
	public AcessList(Access h, List<Access> t) {
		super(h, t);
	}

}
