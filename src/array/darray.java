package array;

// 28/09/2016
// dynamic array
//
public class darray {

	private numClass[] arr;  //memory
	private int max = 10;    //initial memories size
	private int size = 0;    //current arrays size 
	private int lastindex=0; //use along with findNumClass that show if something was finded
	
	//initialize the array
	public void create(){
		arr = new numClass[max];
		for(int i=0; i<max; i++){
			arr[i]=null;
		}
	}
	
	//put an element in the array
	//the object will be stored in the numClass of the slot array
	public void put(int pi, Object o){
		int i, z, nv;
		numClass ntmp, num, lnm[];
		
		//first, find it
		ntmp = this.findNumClass(pi);
		if(ntmp!=null){
			ntmp.o = o; //change
			return;
		}
		
		//well, insert
		num = new numClass();
		size = size + 1;
		if(size > max){
			//allocate new memory, about plus 33%
			nv = max + ( max / 3 ); ////max + 10; // ( max * ( 2/3 ) );
			lnm = new numClass[nv];
			max = nv;
			
			for(z=0; z<arr.length; z++){
				lnm[z]=arr[z];
			}
			arr = lnm;
		}
		
		//last index, come from findNumClass
		//without this trick, all elements would be scanned
		for(i=lastindex;i<max;i++){
			if(arr[i]!=null){
				if(arr[i].num>pi){
					//move elements from this point one slot
					for(int z1=size-1;z1>i; z1--){
						arr[z1]=arr[z1-1];
					}
					num.num = pi;
					num.o = o;
					arr[i]=num;
					return;
				}
			}else{
				num.num = pi;
				num.o = o;
				arr[i]=num;
				return;
			}
		}
	}
	
	//get an element of the memory, physical position
	private int get(int idx){
		numClass nc = arr[idx];
		if(nc==null){
			return 0;
		}
		return((int)nc.num);
	}
	
	//find de objeto, returns null if did not finded
	public Object find(int pidx){
		int i1, i2, ix;
		numClass nm;
		
		//empty
		if(size==0){
			return null;
		}
		
		//binary search
		i1 = 0;
		i2 = size-1;
		while(true){
			nm = arr[i1];
			if(nm.num == pidx){
				return(nm.o);
			}else{
				nm = arr[i2];
				if(nm.num == pidx){
					return(nm.o);
				}
				ix = (i2-i1)/2; //divide, set to the middle
				nm = arr[i2-ix];
				if(nm.num == pidx){
					return(nm.o);
				}else{
					if(pidx>nm.num){
						i1 = i1+ix;
					}else{
						i2 = i2-ix;
					}
				}
			}
			if(ix==0){
				return(null);
			}
		}
	}
	
	//like as find, but instead the object, return the numClass
	//if did not finded, return null and lastindex is changed to 0
	private numClass findNumClass(int pidx){
		int i1, i2, ix;
		numClass nm;
		
		//empty
		if(size==0){
			this.lastindex = 0;
			return null;
		}
		
		//binary search
		i1 = 0;
		i2 = size-1;
		while(true){
			nm = arr[i1];
			if(nm.num == pidx){
				return(nm);
			}else{
				nm = arr[i2];
				if(nm.num == pidx){
					return(nm);
				}
				ix = (i2-i1)/2;
				nm = arr[i2-ix];
				if(nm.num == pidx){
					return(nm);
				}else{
					if(pidx>nm.num){
						i1 = i1+ix;
					}else{
						i2 = i2-ix;
					}
				}
			}
			if(ix==0){
				//well, did not find
				// set lastindex with last point 
				lastindex = i2;
				return(null);
			}
		}
	}
	
	//list all elements of the array
	public void list(){
		for(int i=0;i<size;i++){
			System.out.println(get(i));
		}
	}
	
	public Object getObjectRealIndex(int i){
		if(i>size-1){
			return(null);
		}else{
			return(get(i));
		}
	}
	
	public int getSize(){
		return size;
	}
	
	//test the program
	public static void main(String[] argv){
		
		darray d = new darray();
		d.create();
		d.put(10, null);
		d.put(11, null);
		d.put(50, null);
		d.put(51, null);
		d.put(52, null);
		d.put(53, null);
		d.put(101, null);
		d.put(102, null);
		d.put(103, null);
		d.put(104, "teste");
		d.put(105, null);
		d.put(106, null);
		d.put(107, null);
		
		d.list();
		System.out.print("achou"+d.find(104));
		
	}
	

}