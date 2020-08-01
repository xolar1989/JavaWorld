package carlos.Application;

import carlos.wirtual_life.Direction;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.animals.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Window extends JFrame implements ActionListener , KeyListener {
    private static final int FieldSize = 40;

    private JMenuBar menuBar ;
    private JMenu menuGame  , menuInfo ,menuHelp ;
    private JMenuItem newGameO , saveO ,loadO , exitO , organismsO   , aboutO;

    World world ;
    JPanel board ;
    Graphics2D g ;

    LegendWindow legendWindow = new LegendWindow() ;



    public Window(World world){

        this.world = world ;
        setTitle("Carlos Witual Life");

        setSize(world.getWidth()*FieldSize+FieldSize/2 ,(world.getHeight()+1)*FieldSize + FieldSize/2 );
        setLocation(400,50);
        getContentPane().setBackground(Color.RED);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        menuBar = new JMenuBar() ;
        menuGame = new JMenu("Game") ;
        menuHelp = new JMenu("Help") ;
        menuInfo = new JMenu("Info") ;

       newGameO = new JMenuItem("New") ;
       saveO = new JMenuItem("Save") ;
       loadO = new JMenuItem("Load") ;
       exitO = new JMenuItem("Exit");


       menuGame.add(newGameO) ;
       menuGame.add(loadO) ;
       menuGame.add(saveO) ;
       menuGame.add(exitO) ;
        addKeyListener(this);

       exitO.addActionListener(this);
       exitO.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));


       organismsO = new JMenuItem("Organisms") ;


       menuInfo.add(organismsO) ;


       organismsO.addActionListener(this) ;


       aboutO = new JMenuItem("About Program") ;
       menuHelp.add(aboutO) ;

        setJMenuBar(menuBar);
        menuBar.add(menuGame);
        menuBar.add(menuInfo);
        menuBar.add(menuHelp);


//        superPowerCheckBox = new JCheckBox("Super Moc") ;
//        superPowerCheckBox.setBounds(width-150 , 100 , 150 ,20 );
//        add(superPowerCheckBox) ;



        this.board = new Board(this) ;
        add(this.board) ;


        setVisible(true);
        board.setSize(world.getWidth()*FieldSize ,world.getHeight()*FieldSize);

        this.g = (Graphics2D) this.board.getGraphics() ;










    }

    private void endProgram(){
        legendWindow.dispose();
        dispose();
    }


    public void printBoard(){
        g.setColor(Color.RED);
        g.fillRect(0,0,world.getWidth()*FieldSize+50 ,world.getHeight()*FieldSize +50);
        for (int y = 0; y < world.getHeight() ; y++) {
            for (int x = 0; x < world.getWidth() ; x++) {
                printField(y,x);
            }
        }


    }

    private void printField(int y,int x){
        Organism organism = world.getOrganism(y,x) ;

        if(organism != null){
            organism.character().draw(g ,x,y , this) ;
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource() ;
        if(source==exitO){
            endProgram();
        }
        else if(source==organismsO){
            legendWindow.open();
        }

    }



    @Override
    public void keyPressed(KeyEvent e) {
        Direction.DirectionBase direction = null;
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
            direction = Direction.DirectionBase.TOP ;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            direction = Direction.DirectionBase.LEFT ;
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
            direction = Direction.DirectionBase.RIGHT ;
        else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            direction = Direction.DirectionBase.BOTTOM ;
        else if(e.getKeyCode()==KeyEvent.VK_G){
            if(world.hero.isAlive()) ((Human)world.hero ).switchSuperPower();
        }
        else if(e.getKeyCode()==KeyEvent.VK_P){
            try {
                world.saveGame();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }



        if(direction != null && !world.makeTurn(direction) ){
            System.out.println("cos pyklo");
            endProgram();
        }
        else {
            printBoard();
        }


    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
}
