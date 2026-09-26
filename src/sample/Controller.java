package sample;

import javafx.animation.FadeTransition;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Controller
{
    static int roll;
    private Stage stage;
    private boolean flag = false;
    boolean player1_move = false;
    boolean player2_move = true;

    @FXML
    private ImageView Dice_arrow;

    @FXML
    private ImageView Back_menu;

    @FXML
    private ImageView Piece_1;

    @FXML
    private ImageView Piece_2;

    @FXML
    private ImageView Player_1_image_view;

    @FXML
    private ImageView Player_2_image_view;

    @FXML
    private Label label_1;

    @FXML
    private Label label_2;

    @FXML
    private ImageView Dice_view_changer;

    @FXML
    private ImageView Choosing_menu;

    @FXML
    private Button Play2;

    @FXML
    private TextField Player_1_text_field;

    @FXML
    private TextField Player_2_text_field;

    @FXML
    private ImageView Congratulation;

    @FXML
    private Label Winner_label;

    @FXML
    private Label Loser_label;

    @FXML
    private Button Menu_return;

    @FXML
    private Button replay;

    @FXML
    private Button Back_to_board;

    @FXML
    private Button return_to_menu;

    @FXML
    private AnchorPane Board;

    @FXML
    private Button Back;

    @FXML
    private ImageView Entry_Arrow;

    @FXML
    private Button Dice;

    @FXML
    private ImageView dim_player_1;

    @FXML
    private ImageView dim_player_2;

    private boolean flag2=false;

    @FXML
    public void Quit(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    @FXML
    void exit_pop_up(ActionEvent event)
    {
        FadeTransition fade1 = new FadeTransition();
        FadeTransition fade2 = new FadeTransition();
        FadeTransition fade3 = new FadeTransition();
        FadeTransition fade4 = new FadeTransition();
        FadeTransition fade5 = new FadeTransition();
        FadeTransition fade6 = new FadeTransition();
        FadeTransition fade7 = new FadeTransition();
        FadeTransition fade8 = new FadeTransition();
        FadeTransition fade9 = new FadeTransition();
        FadeTransition fade10 = new FadeTransition();
        FadeTransition fade11 = new FadeTransition();
        FadeTransition fade12 = new FadeTransition();
        FadeTransition fade13 = new FadeTransition();

        call_transition(fade1, Board,0.2,-1);
        call_transition(fade2, Dice_view_changer,0.2,-1);
        call_transition(fade3, Back,0.2,-1);
        call_transition(fade4, Entry_Arrow,0.2,-1);
        call_transition(fade5, Piece_1,0.2,-1);
        call_transition(fade6, Piece_2,0.2,-1);
        call_transition(fade7, Piece_2,0.2,-1);
        call_transition(fade8, Dice_arrow,0.2,-1);
        call_transition(fade9, Dice,0.2,-1);
        call_transition(fade10, Player_1_image_view,0.2,-1);
        call_transition(fade11, Player_2_image_view,0.2,-1);
        call_transition(fade12, label_1,0.2,-1);
        call_transition(fade13, label_2,0.2,-1);

        TranslateTransition transition = new TranslateTransition();
        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();

        call_transition(transition, Back_menu, -1000);
        call_transition(transition1, Back_to_board, -1000);
        call_transition(transition2, return_to_menu, -1000);
    }

    @FXML
    void getCoordinates(MouseEvent event)
    {
        label_1.setText(Main.p1.getName());
        label_2.setText(Main.p2.getName());
    }

    @FXML
    void Dice_roll(ActionEvent event)
    {
        if (!flag)
        {
            flag = true;
            new Thread(() ->
            {
                TranslateTransition transition = new TranslateTransition();
                transition.setDuration(Duration.millis(500));
                transition.setNode(Dice_arrow);
                transition.setByY(14);
                transition.setAutoReverse(true);
                transition.setCycleCount(Timeline.INDEFINITE);
                transition.play();
            }).start();
        }

        Dice dice = new Dice();
        roll = dice.getVal();
        Image img = new Image("Dice_face_"+roll+".png");
        Dice_view_changer.setImage(img);
        //disable the image next to the dice for player
        if (!player1_move)
        {
            FadeTransition ft = new FadeTransition();
            call_transition(ft,Player_2_image_view,1,-1);
            ft = new FadeTransition();
            call_transition(ft,Player_1_image_view,0.2,-1);
            dim_player_2.setVisible(false);
            dim_player_1.setVisible(true);
            ft = new FadeTransition();
            call_transition(ft,dim_player_1,1,-1);
            ft = new FadeTransition();
            call_transition(ft,dim_player_2,1,-1);
            int winner=move_player(roll,Main.p1,Main.board);
            player1_move=true;
            player2_move=false;
            if (winner==1)
            {
                winner(Main.p1,Main.p2);
            }
            return;
        }
        if (!player2_move)
        {
            FadeTransition ft=new FadeTransition();
            call_transition(ft,Player_1_image_view,1,-1);
            ft= new FadeTransition();
            call_transition(ft,Player_2_image_view,0.2,-1);
            ft = new FadeTransition();
            call_transition(ft,dim_player_1,1,-1);
            ft = new FadeTransition();
            call_transition(ft,dim_player_2,1,-1);
            dim_player_1.setVisible(false);
            dim_player_2.setVisible(true);
            int winner=move_player(roll,Main.p2,Main.board);
            player1_move=false;
            player2_move=true;
            if (winner==1)
            {
                winner(Main.p2,Main.p1);
            }
        }
    }

    @FXML
    public void New_Game(ActionEvent actionEvent)
    {
        TranslateTransition transition = new TranslateTransition();
        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();
        TranslateTransition transition3 = new TranslateTransition();
        call_transition(transition, Choosing_menu, -410);
        call_transition(transition1, Player_1_text_field, -410);
        call_transition(transition2, Player_2_text_field, -410);
        call_transition(transition3, Play2, -410);
    }

    @FXML
    void return_menu(ActionEvent event)
    {
        try
        {
            stage = Main.Stage;
            switchScene(stage,"menu.fxml");
        } catch (Exception e) {}
    }

    @FXML
    void restart(ActionEvent event)
    {
        resetGame();
        try
        {
            stage = Main.Stage;
            switchScene(stage,"Board.fxml");
        } catch (Exception e) {}
    }

    @FXML
    void Menu_will_return(ActionEvent event)
    {
        try
        {
            stage = Main.Stage;
            switchScene(stage,"menu.fxml");
        } catch (Exception e) {}
    }

    @FXML
    void return_to_board(ActionEvent event)
    {
        FadeTransition fade1 = new FadeTransition();
        FadeTransition fade2 = new FadeTransition();
        FadeTransition fade3 = new FadeTransition();
        FadeTransition fade4 = new FadeTransition();
        FadeTransition fade5 = new FadeTransition();
        FadeTransition fade6 = new FadeTransition();
        FadeTransition fade7 = new FadeTransition();
        FadeTransition fade8 = new FadeTransition();
        FadeTransition fade9 = new FadeTransition();
        FadeTransition fade10 = new FadeTransition();
        FadeTransition fade11 = new FadeTransition();
        FadeTransition fade12 = new FadeTransition();
        FadeTransition fade13 = new FadeTransition();

        call_transition(fade1, Board,1,-1);
        call_transition(fade3, Back,1,-1);
        call_transition(fade4, Entry_Arrow,1,-1);
        call_transition(fade5, Piece_1,1,-1);
        call_transition(fade6, Piece_2,1,-1);
        call_transition(fade7, Piece_2,1,-1);
        call_transition(fade8, Dice_arrow,1,-1);
        call_transition(fade9, Dice,1,-1);
        call_transition(fade10, Player_1_image_view,1,-1);
        call_transition(fade11, Player_2_image_view,1,-1);
        call_transition(fade12, label_1,1,-1);
        call_transition(fade13, label_2,1,-1);
        call_transition(fade2, Dice_view_changer,1,-1);
        TranslateTransition transition = new TranslateTransition();
        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();
        call_transition(transition, Back_menu, 1000);
        call_transition(transition1, Back_to_board, 1000);
        call_transition(transition2, return_to_menu, 1000);
    }

    boolean isValid(String name)
    {
        // returns true if name contains spaces, digits and letters
        return name.matches("[a-zA-Z0-9 ]+");
    }

    public void switchScene(Stage stage,String url) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public int move_player(int x,Player p1,Objects[] board)
    {
        double offset_x=54.75;
        double offset_y=49.75;
        if (p1.getStatus())
        {

            if (p1.getTile() + x > 100)
                return 0;
            for (int i = 1; i <= x; i++)
            {

                Pair p = Main.map.get(p1.getTile());
                p1.setTile(p1.getTile() + 1);

                if (p.getX()%2 == 1)
                {

                    if (p1.getRole().equals("p1"))
                    {
                        if (p.getY()!=9)
                        {
                            //transition right
                            Piece_1.setX(Piece_1.getX() + offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_1.setY(Piece_1.getY() - offset_y);
                        }
                    }
                    else
                    {
                        if (p.getY()!=9)
                        {
                            //transition right
                            Piece_2.setX(Piece_2.getX()+offset_x);
                        }
                        else {
                            //transition above
                            Piece_2.setY(Piece_2.getY()-offset_y);
                        }
                    }
                }
                else
                {

                    if (p1.getRole().equals("p1"))
                    {
                        if (p.getY()!=0)
                        {
                            //transition left
                            Piece_1.setX(Piece_1.getX()-offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_1.setY(Piece_1.getY()-offset_y);
                        }
                    }
                    else
                    {
                        if (p.getY()!=0)
                        {
                            //transition left
                            Piece_2.setX(Piece_2.getX()-offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_2.setY(Piece_2.getY()-offset_y);
                        }
                    }
                }
            }

            if (p1.isWinner())
            {
                return 1;
            }
            Object obj = board[p1.getTile()-1];

            if (obj instanceof Ladders)
            {
                p1.setTile( ((Ladders) obj).getEnd() );
                int start_tile=((Ladders) obj).getStart();
                Pair start=Main.map.get(start_tile);
                int end_tile=((Ladders) obj).getEnd();
                Pair end=Main.map.get(end_tile);
                int diff_x=end.getY()-start.getY();
                int diff_y=end.getX()-start.getX();
                // I have stored in i,j in the opposite order of the conventional x and y coordinates followed in maths so, I need to take care of it

                if (p1.getRole().equals("p1"))
                {
                    //move piece 1
                    Piece_1.setX(Piece_1.getX()+offset_x*diff_x);
                    Piece_1.setY(Piece_1.getY()+offset_y*diff_y);
                }
                else
                {
                    //do the same but for piece 2
                    Piece_2.setX(Piece_2.getX()+offset_x*diff_x);
                    Piece_2.setY(Piece_2.getY()+offset_y*diff_y);
                }
            }
            else if (obj instanceof Snakes)
            {
                p1.setTile( ((Snakes) obj).getEnd() );
                // do everything similar to ladders
                int start_tile = ((Snakes) obj).getStart();
                Pair start = Main.map.get(start_tile);
                int end_tile = ((Snakes) obj).getEnd();
                Pair end = Main.map.get(end_tile);

                int diff_x=end.getY()-start.getY();
                int diff_y=end.getX()-start.getX();

                if (p1.getRole().equals("p1"))
                {
                    //move piece 1
                    Piece_1.setX(Piece_1.getX()+offset_x*diff_x);
                    Piece_1.setY(Piece_1.getY()+offset_y*diff_y);
                }
                else
                {
                    //do the same but for piece 2
                    Piece_2.setX(Piece_2.getX()+offset_x*diff_x);
                    Piece_2.setY(Piece_2.getY()+offset_y*diff_y);
                }
            }
        }
        else
        {

            if (x==1)
            {
                p1.setStatus(true);
                p1.setTile(1);
                TranslateTransition t=new TranslateTransition();
                t.setDuration(Duration.millis(250));
                t.setAutoReverse(false);
                t.setCycleCount(1);
                if (p1.getRole().equals("p1"))
                {
                    t.setNode(Piece_1);
                    t.setByY(-offset_y);
                }
                else
                {
                    //do the same but for piece 2
                    t.setNode(Piece_2);
                    t.setByY(-offset_y);
                }
                t.play();
            }
        }
        return 0;
    }

    void winner(Player p1,Player p2)
    {
        //first argument is winner call for transitions here for the congratulations screen
        FadeTransition fade1 = new FadeTransition();
        FadeTransition fade2 = new FadeTransition();
        FadeTransition fade3 = new FadeTransition();
        FadeTransition fade4 = new FadeTransition();
        FadeTransition fade5 = new FadeTransition();
        FadeTransition fade6 = new FadeTransition();
        FadeTransition fade7 = new FadeTransition();
        FadeTransition fade8 = new FadeTransition();
        FadeTransition fade9 = new FadeTransition();
        FadeTransition fade10 = new FadeTransition();
        FadeTransition fade11 = new FadeTransition();
        FadeTransition fade12 = new FadeTransition();
        FadeTransition fade13 = new FadeTransition();

        call_transition(fade1, Board,0.2,-1);
        call_transition(fade3, Back,0.2,-1);
        call_transition(fade4, Entry_Arrow,0.2,-1);
        call_transition(fade5, Piece_1,0.2,-1);
        call_transition(fade6, Piece_2,0.2,-1);
        call_transition(fade7, Piece_2,0.2,-1);
        call_transition(fade8, Dice_arrow,0.2,-1);
        call_transition(fade9, Dice,0.2,-1);
        call_transition(fade10, Player_1_image_view,0.2,-1);
        call_transition(fade11, Player_2_image_view,0.2,-1);
        call_transition(fade12, label_1,0.2,-1);
        call_transition(fade13, label_2,0.2,-1);
        call_transition(fade2, Dice_view_changer,0.2,-1);
        Winner_label.setText(p1.getName());
        Loser_label.setText(p2.getName());
        TranslateTransition transition = new TranslateTransition();
        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();
        TranslateTransition transition3 = new TranslateTransition();
        TranslateTransition transition4 = new TranslateTransition();
        call_transition(transition, Congratulation, -625);
        call_transition(transition1, Winner_label, -625);
        call_transition(transition2, Loser_label, -625);
        call_transition(transition3, Menu_return, -625);
        call_transition(transition4, replay, -625);
    }

    public void resetGame()
    {
        Main.p1.setTile(1);
        Main.p1.setStatus(false);
        Main.p1.setWinner(false);
        Main.p2.setTile(1);
        Main.p2.setStatus(false);
        Main.p2.setWinner(false);
        player1_move=false;
        player2_move=true;
        flag=false;
    }

    public void call_transition(FadeTransition transition, Node id, double value,double duration)
    {
        transition.setDuration(Duration.millis(10));
        transition.setNode(id);
        transition.setFromValue(1.0);
        transition.setToValue(value);
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.play();
    }

    public void call_transition(TranslateTransition transition, Node id, int value)
    {
        transition.setDuration(Duration.millis(1));
        transition.setNode(id);
        transition.setByY(value);
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.play();
    }

    public void show_Board(ActionEvent actionEvent)
    {

        if (Player_1_text_field.getText().equals(""))
        {
            Main.p1.setName("Player 1");
        }
        else
        {
            if (isValid(Player_1_text_field.getText()))
            {
                Main.p1.setName(Player_1_text_field.getText());
            }
            else
            {
                Main.p1.setName("Player 1");
            }
        }

        if (Player_2_text_field.getText().equals(""))
        {
            Main.p2.setName("Player 2");
        }
        else
        {
            if (isValid(Player_2_text_field.getText()))
            {
                Main.p2.setName(Player_2_text_field.getText());
            }
            else
            {
                Main.p2.setName("Player 2");
            }
        }

        try
        {
            stage = Main.Stage;
            switchScene(stage,"Board.fxml");
            resetGame();
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.millis(500));
            transition.setNode(Dice_arrow);
            transition.setByY(14);
            transition.setAutoReverse(true);
            transition.setCycleCount(Timeline.INDEFINITE);
            transition.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
