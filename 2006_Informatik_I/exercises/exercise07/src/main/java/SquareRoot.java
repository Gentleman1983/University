public class SquareRoot 
{ 
    private static double calc_ite(double d, double from, 
             double to, int steps) 
    { 
         while (steps > 0) 
         { 
            if (d > (from + to) * (from + to) / 4) 
            { 
                from = (from + to)/2; 
            } 
            else 
            { 
                to = (from + to)/2; 
            } 
            steps--; 
         } 
         return (from + to) / 2; 
    } 
  
    private static double calc_rec(double d, double from, 
            double to, int depth) 
    { 
        if (depth < 1)
        {
            return (from + to) / 2;
        }
        return (d > (from + to) * (from + to) / 4) ? 
            calc_rec(d, (from + to) / 2, to, --depth) : 
            calc_rec(d, from, (from + to) / 2, --depth); 
    } 
  
    public static void calc(double d, int steps) 
    { 
        System.out.println(calc_rec(d, 0.0, d, steps)); 
        System.out.println(calc_ite(d, 0.0, d, steps)); 
    } 
} 