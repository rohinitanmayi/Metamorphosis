import java.util.*;

class Outfit {

    String name;
    String makeup;
    String hair;
    String accessories;

    Outfit(String n,String m,String h,String a){
        name=n;
        makeup=m;
        hair=h;
        accessories=a;
    }

}

public class MetamorphosisDSA {

    static Scanner sc=new Scanner(System.in);

    /* STACK for viewing history */
    static Stack<String> viewStack=new Stack<>();

    /* QUEUE for feed */
    static Queue<Outfit> feedQueue=new LinkedList<>();

    static List<Outfit> outfits=new ArrayList<>();


    /* DATABASE */

    static String[] fits={
    "Midnight Leather","Cyber Street","Soft Blossom","Urban Cargo",
    "Velvet Night","Angel Core","Neon Runner","Rose Garden",
    "Vintage Noir","Tokyo Pop","Future Muse","Street Ghost",
    "Dark Academia","Pearl Glow","Canvas Street","Sunset Lounge",
    "Retro Pop","Night Rider","Modern Muse","Chrome Edge"
    };

    static String[] makeup={
    "Smokey liner","Gloss lips","Clean skin","Soft blush","Matte gothic lip"
    };

    static String[] hair={
    "Loose waves","High ponytail","Slick bun","Braided style","Layered cut"
    };

    static String[] accessories={
    "Silver rings","Platform boots","Canvas tote","Choker","Retro shades"
    };


    /* GENERATE OUTFITS */

    static void generateDatabase(){

        for(int i=0;i<20;i++){

            outfits.add(new Outfit(
            fits[i],
            makeup[i%makeup.length],
            hair[i%hair.length],
            accessories[i%accessories.length]
            ));

        }

    }


    /* QUICK SORT */

    static List<Outfit> quickSort(List<Outfit> arr){

        if(arr.size()<=1) return arr;

        Outfit pivot=arr.get(arr.size()-1);

        List<Outfit> left=new ArrayList<>();
        List<Outfit> right=new ArrayList<>();

        for(int i=0;i<arr.size()-1;i++){

            if(arr.get(i).name.compareTo(pivot.name)<0)
                left.add(arr.get(i));
            else
                right.add(arr.get(i));

        }

        List<Outfit> sorted=new ArrayList<>();

        sorted.addAll(quickSort(left));
        sorted.add(pivot);
        sorted.addAll(quickSort(right));

        return sorted;

    }


    /* BINARY SEARCH */

    static int binarySearch(List<Outfit> arr,String target){

        int left=0;
        int right=arr.size()-1;

        while(left<=right){

            int mid=(left+right)/2;

            int cmp=arr.get(mid).name.compareToIgnoreCase(target);

            if(cmp==0)
                return mid;

            if(cmp<0)
                left=mid+1;
            else
                right=mid-1;

        }

        return -1;

    }


    /* LOAD FEED USING QUEUE */

    static void loadFeed(){

        feedQueue.clear();

        for(Outfit o:outfits)
            feedQueue.add(o);

        List<Outfit> sorted=quickSort(new ArrayList<>(feedQueue));

        System.out.println("\n---- Inspiration Feed ----");

        for(int i=0;i<sorted.size();i++){

            System.out.println((i+1)+". "+sorted.get(i).name);

        }

    }


    /* VIEW OUTFIT */

    static void viewOutfit(int index){

        Outfit fit=outfits.get(index);

        viewStack.push(fit.name);

        System.out.println("\n--- Outfit Detail ---");

        System.out.println("Name: "+fit.name);
        System.out.println("Makeup: "+fit.makeup);
        System.out.println("Hair: "+fit.hair);
        System.out.println("Accessories: "+fit.accessories);

    }


    /* SEARCH OUTFIT */

    static void searchOutfit(){

        System.out.print("Enter outfit name: ");
        sc.nextLine();
        String name=sc.nextLine();

        List<Outfit> sorted=quickSort(new ArrayList<>(outfits));

        int index=binarySearch(sorted,name);

        if(index==-1)
            System.out.println("Outfit not found");
        else{

            Outfit fit=sorted.get(index);

            viewStack.push(fit.name);

            System.out.println("\nFound Outfit:");
            System.out.println("Name: "+fit.name);
            System.out.println("Makeup: "+fit.makeup);
            System.out.println("Hair: "+fit.hair);
            System.out.println("Accessories: "+fit.accessories);

        }

    }


    /* VIEW HISTORY (STACK) */

    static void viewHistory(){

        if(viewStack.isEmpty()){
            System.out.println("No outfits viewed yet");
            return;
        }

        System.out.println("\nViewing History:");

        for(String s:viewStack)
            System.out.println(s);

    }


    public static void main(String[] args){

        generateDatabase();

        int choice;

        do{

            System.out.println("\n---- Metamorphosis Fashion Engine ----");
            System.out.println("1. Load Inspiration Feed");
            System.out.println("2. View Outfit");
            System.out.println("3. Search Outfit");
            System.out.println("4. View History");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            choice=sc.nextInt();

            switch(choice){

                case 1:
                    loadFeed();
                    break;

                case 2:
                    System.out.print("Enter outfit number: ");
                    int n=sc.nextInt();
                    viewOutfit(n-1);
                    break;

                case 3:
                    searchOutfit();
                    break;

                case 4:
                    viewHistory();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice");

            }

        }while(choice!=5);

    }

}
