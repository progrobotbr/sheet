package parse;

import java.util.Stack;

import sheet.sheet;
public class parse {
	
	public double d1;
	private int index;
	private int size;
	private byte data[]=null;
	private sheet sheet=null;
	
	public void setSheet(sheet psheet){
		sheet = psheet;
	}
	
	public void exec(int pcol, int plin){
		int i1, i2;
		double res=0;
		String tok=null;
		String sop="";
		String s;
		String snum1,snum2, sres;
		Stack <String>op=new Stack<String>(), ress=new Stack<String>();
		
		if(sheet == null){
			return;
		}
		
		s = (String) sheet.get(pcol, plin);
		if(s==null){
			return;
		}
		
		index = 0;
		data = s.getBytes();
		size=data.length;
		
		tok=getToken();
		
		if(tok!=null && tok.equals("=")){
			
			tok=getToken();
			
			while(tok!=null){
			
				if(!isop(tok)){
					ress.push(tok);
				}else{
					if(op.size()==0){
						op.push(tok);
						tok=getToken();
						continue;
					}
					if(tok.equals("(")){
						op.push(tok);
						tok=getToken();
						continue;
					}
					if(tok.equals(")")){
						sop = (String) op.pop();
						while(op.size()>0 && !sop.equals("(")){
							snum2 = (String)ress.pop();
							snum1 = (String)ress.pop();
							res = calc(snum1,snum2, sop);
							sres = ""+res;
							ress.push(sres);
							sop = (String) op.pop();
						}
					}else{
						while(op.size()>0){
							sop = (String) op.pop();
							i1=prioridade(sop);
							i2=prioridade(tok);
							if(i2<=i1){
								snum2 = (String)ress.pop();
								if(snum2!=null){
									snum1 = (String)ress.pop();
									res = calc(snum1,snum2, sop);
									sres = ""+res;
									ress.push(sres);
								}
							}else{
								op.push(sop);
								break;
							}
						}
					
						op.push(tok);
					}
				}				
				tok=getToken();
			}
            
			while(op.size()>0){
				sop=op.pop();
				snum2 = (String)ress.pop();
				if(snum2!=null){
					snum1 = (String)ress.pop();
					res = calc(snum1, snum2, sop);
					sres = ""+res;
					ress.push(sres);
				}	
			}
			sres = ress.pop();
			//System.out.println(sres);	
			sheet.put(pcol, plin, sres);

		}else{
			//System.out.println(tok);
			sheet.put(pcol, plin, tok);
		}
           		
	}
	
	public double calc(String num1, String num2, String op){
		double d1=0, d2=0;
		try{
			d1 = Float.parseFloat(num1);
			d2 = Float.parseFloat(num2);
		}catch(Exception ex){
		}	
		switch(op){
		case "+":
			d1+=d2;
			return(d1);
		case "-":
			d1-=d2;
			return(d1);
		case "*":
			d1*=d2;
			return(d1);
		case "/":
			d1/=d2;
			return(d1);
		}
		return(0);
	}
	
	public int prioridade(String tok){
		switch(tok){
			case "+":
				return(1);
			case "-":
				return(1);
			case "*":
				return(2);
			case "/":
				return(2);
			case "(":
				return(0);
			default:
				return(0);
		}
	}	
	
	public boolean isop(String tok){
		switch(tok){
		case "+":
			return(true);
		case "-":
			return(true);
		case "*":
			return(true);
		case "/":
			return(true);
		case "(":
			return(true);
		case ")":
			return(true);
		default:
			return(false);
		}
	}
	
	public String getToken(){
		byte bchar;
		String sword="";
		if(index>=size){
			return null;
		}
		while(index<size){
			bchar=data[index];
			switch(bchar){
			case '=':
				sword="=";
				index++;
				return sword;
			case '+':
				sword="+";
				index++;
				return sword;
			case '-':
				sword="-";
				index++;
				return sword;
			case '/':
				sword="/";
				index++;
				return sword;
			case '*':
				sword="*";
				index++;
				return sword;
			case '(':
				sword="(";
				index++;
				return sword;
			case ')':
				sword=")";
				index++;
				return sword;
			case ' ':
				index++;
				break;
			default:
				byte[] bb = { bchar };
				sword += new String(bb);
				index++;
				break;
			}
			if(index>=size){
				return sword;
			}
			bchar=data[index];
			if(!sword.equals(""))
			if(bchar=='-' ||
			   bchar=='+' ||
			   bchar=='*' ||
			   bchar=='/' ||
			   bchar=='=' ||
			   bchar=='(' ||
			   bchar==')'){
				return sword;
			}
		}
		return sword;
		
	}


}
