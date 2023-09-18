package sheet;

import parse.parse;
import array.darray;
import array.numClass;

public class sheet {

	darray coluna = new darray();
	darray linha  = new darray();
	
	public static void main(String[] args) {
		
		sheet tst = new sheet();
		tst.create();
		String s = "RENATO", d;
		tst.put(20, 10, s);
		tst.put(40, 10, "teste");
		tst.put(70, 10, "lima");
		tst.put(70, 5, "rows");
		tst.put(99, 99, "fim");
		tst.put(1, 1, "INICIO");
		tst.put(1, 2, "BBBB");
		tst.put(2, 2, "AAAA");
		tst.put(2, 3, "CCCC");
		d = (String) tst.get(40, 10);
		System.out.println(d);
		
		tst.put(40, 10, "juca");
		
		d = (String) tst.get(40, 10);
		System.out.println(d);
		
		
		/*
		darray da = tst.getLinha(10);
		System.out.println("linha");da.list();
		da = tst.getColuna(20);
		System.out.println("coluna");da.list();
		*/
		
		int limit = 1000;
		/*
		
		for(int i=0;i<limit;i++){
			for(int z=0; z<limit; z++){
				Object jj = tst.get(z, i);
				if(jj==null){
					System.out.print("|");
				}else{
					System.out.print(tst.get(z, i)+"|");
				}
			}
			System.out.println("");
		}
		*/
				
		long lhri = System.currentTimeMillis();
		
		for(int i=1;i<limit;i++){
			for(int z=1; z<limit; z++){
				s = (String) tst.get(z, i);
				if(s==null){
					//Integer it =new Integer(z);
					tst.put(z, i, "="+z+"+(4-"+i+"*(3-(6*(4-5)))-5)*5-1");
				}
				//System.out.println("coluna:"+z);
			}
			//System.out.println("linha:"+i);
		}

		//System.out.println("");
		parse ps = new parse();
		for(int i=1;i<limit;i++){
			System.out.print("linha:"+i+" -"+ "="+1+"+(4-"+i+"*(3-(6*(4-5)))-5)*5-1");
			for(int z=1; z<limit; z++){
				Object jj = tst.get(z, i);
				if(jj==null){
					System.out.print("|");
				}else{
					
					ps.setSheet(tst);
					ps.exec(z, i);
					System.out.print(tst.get(z, i)+"|");
				}
			}
			System.out.println("");
		}
		
		long lhrf=System.currentTimeMillis();
		System.out.println(""+lhri);
		System.out.println(""+lhrf);
		
		//tst.put(10, 10, "=2+(4-1*(3-(6-8))-5)*5-1");
		//tst.put(10, 10, "=2+4*(5-3)*2");
		//parse ps = new parse();
		//ps.setSheet(tst);
		//ps.exec(10, 10);
		//System.out.println("resultado :" + tst.get(10, 10));
	}
	
	//cria coluna e linha mestre
	public void create(){
		coluna.create();
		linha.create();
	}
	
	//insere um objeto na planilha
	public void put(int pcoluna, int plinha, Object o){
		numClass nm;
		darray dar;
		
		dar = (darray) coluna.find(pcoluna);
		if(dar!=null){
			dar.put(plinha, o);
		}else{
			nm = new numClass();
			nm.o = o;
			dar = new darray();
			dar.create();
			coluna.put(pcoluna, dar);
			dar.put(plinha, o);
		}
		
		dar = (darray)linha.find(plinha);
		if(dar!=null){
			dar.put(pcoluna, o);
		}else{
			nm = new numClass();
			nm.o = o;
			dar = new darray();
			dar.create();
			linha.put(plinha, dar);
			dar.put(pcoluna, o);
		}
	}
	
	//pega um objeto da planilha
	public Object get(int pcoluna, int plinha ){
		darray dar;
		Object o;
		dar = (darray)coluna.find(pcoluna);
		if(dar!=null){
			o = dar.find(plinha);
			if(o!=null){
				return(o);
			}
		}
		return(null);
	}
	
	public darray getLinha(int plinha){
		return(linha);
	}
	
	public darray getColuna(int pcoluna){
		return(coluna);
	}
	
	public void insertColumn(int pcolumn, int qty){
		
	}

}
