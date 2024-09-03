import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MusicPlayerGUI extends JFrame {

    public static final Color FRAME_COLOR = new Color(16, 0, 43);
    public static final Color TOOLBAR_COLOR = new Color(151, 157, 172);
    public static final Color TEXT_COLOR = new Color(224, 170, 255);

    public MusicPlayerGUI() {
        super("Music Player");

        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(FRAME_COLOR);
        addGuiComponents();
    }

    private void addGuiComponents() {
        addToolbar();
        JLabel songImage = new JLabel(loadImage("src/assets/music.png"));
        songImage.setBounds(20, 50, getWidth() - 40, 225);
        add(songImage);

        JLabel songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        JLabel songArtist = new JLabel("Artist");
        
        songArtist.setFont(new Font("Dialog", Font.BOLD, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);
    }

    private void addToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(TOOLBAR_COLOR);

        toolBar.setBounds(0, 0, getWidth(), 20);
        toolBar.setFloatable(false);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(TOOLBAR_COLOR);

        toolBar.add(menuBar);
        JMenu songMenu = new JMenu("Song");
        menuBar.add(songMenu);

        JMenuItem loadSong = new JMenuItem("Load Song");
        songMenu.add(loadSong);

        JMenu playlistMenu = new JMenu("PlayList");
        menuBar.add(playlistMenu);

        JMenuItem createPlaylist = new JMenuItem("Create PlayList");
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load PlayList");
        playlistMenu.add(loadPlaylist);

        add(toolBar);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}