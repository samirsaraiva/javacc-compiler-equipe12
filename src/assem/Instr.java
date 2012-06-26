package assem;import Temp.Label;import Temp.Temp;import Temp.TempMap;import util.List;public abstract class Instr {    public String assem;    public List<Temp> use;    public List<Temp> def;    public Integer cons;    public List<Label> jumps;            public String toString(){    	return assem;    }        public void replaceUse(Temp olduse, Temp newuse) throws Exception {	if (use != null)	    for (int i = 0; i< use.size(); i++)			if (use.get(i) == olduse) use.set(i,newuse);	}    public void replaceDef(Temp olddef, Temp newdef) throws Exception {	if (def != null)	    for (int i = 0; i< def.size(); i++)			if (def.get(i) == olddef) def.set(i, newdef);    };    public String format(TempMap m) throws Exception {	StringBuffer s = new StringBuffer();	int len = assem.length();	for(int i=0; i<len; i++)	    if (assem.charAt(i)=='`')		switch(assem.charAt(++i)) {		case 's': {		    int n = Character.digit(assem.charAt(++i),10);		    s.append(m.tempMap(use.get(n)));			break;		}		case 'd': {		    int n = Character.digit(assem.charAt(++i),10);		    s.append(m.tempMap(def.get(n)));		    break;		}		case 'j': {		    int n = Character.digit(assem.charAt(++i),10);		    s.append(jumps.get(n).toString());		    break;		}		case '`':		    s.append('`'); 		    break;		default:		    throw new Error("bad Assem format:" + assem);		}	    else s.append(assem.charAt(i));	return s.toString();    }	public List<Temp> def() {		return def;	}	public List<Temp> use() {		return use;	}}