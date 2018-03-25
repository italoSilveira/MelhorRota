package trabalho;
public class City {
    private int _x;
    private int _y;
    private String _name;
    
    //CONSTRUCTORS
    public City(int x, int y){
    	this("Desconhecido", x, y);
    }
    public City(String name, int x, int y){
    	_name = name;
        _x = x;
        _y = y;
    }
    
    //GETTERS
    public int getX(){
        return _x;
    }
    
    public int getY(){
        return _y;
    }
    public String getName(){
    	return _name;
    }
    
    //METHODS
    //Get the distance to a given city
    public double distanceTo(City city){
        int xDif = Math.abs(this._x - city._x);
        int yDif = Math.abs(this._y - city._y);
        double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
        return distance;
    }
    
    @Override
    public String toString(){
        return ""+_name+"("+_x+","+_y+")";
    }
}
