import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayerGUI extends JFrame {

    public static final Color FRAME_COLOR = new Color(16, 0, 43);
    public static final Color TOOLBAR_COLOR = new Color(151, 157, 172);
    public static final Color TEXT_COLOR = new Color(224, 170, 255);

    private MusicPlayer musicPlayer;
    private JFileChooser jFileChooser;
    private JLabel songTitle, songArtist;
    private JPanel playbackBtns;

    public MusicPlayerGUI() {
        super("Music Player");

        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(FRAME_COLOR);

        musicPlayer = new MusicPlayer();
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("src/assets"));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("MP3", "mp3"));

        addGuiComponents();
    }

    private void addGuiComponents() {
        addToolbar();
        JLabel songImage = new JLabel(loadImage("src/assets/music.png"));
        songImage.setBounds(20, 50, getWidth() - 40, 225);
        add(songImage);

        songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 35);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 315, getWidth() - 10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);

        JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth() / 2 - 300 / 2, 365, 300, 40);
        playbackSlider.setBackground(null);
        add(playbackSlider);

        addPlaybackBtns();
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
        loadSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectFile = jFileChooser.getSelectedFile();

                if (selectFile != null) {
                    Song song = new Song(selectFile.getPath());
                    musicPlayer.loadSong(song);
                    updateSongTitleAndArtist(song);
                    enablePauseButtonDisablePlayButton();
                }
            }
        });
        songMenu.add(loadSong);

        JMenu playlistMenu = new JMenu("PlayList");
        menuBar.add(playlistMenu);

        JMenuItem createPlaylist = new JMenuItem("Create PlayList");
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load PlayList");
        playlistMenu.add(loadPlaylist);

        add(toolBar);
    }

    private void addPlaybackBtns() {
        playbackBtns = new JPanel();
        playbackBtns.setBounds(0, 435, getWidth() - 10, 80);
        playbackBtns.setBackground(null);

        JButton prevButton = new JButton(loadImage("src/assets/previous.png"));
        prevButton.setBorderPainted(false);
        prevButton.setBackground(null);
        playbackBtns.add(prevButton);

        JButton playButton = new JButton(loadImage("src/assets/play.png")); // Example of adding more buttons
        playButton.setBorderPainted(false);
        playButton.setBackground(null);
        playbackBtns.add(playButton);

        JButton pauseButton = new JButton(loadImage("src/assets/pause.png")); // Example of adding more buttons
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(null);
        pauseButton.setVisible(false);
        playbackBtns.add(pauseButton);

        JButton nextButton = new JButton(loadImage("src/assets/next.png"));
        nextButton.setBorderPainted(false);
        nextButton.setBackground(null);
        playbackBtns.add(nextButton);

        add(playbackBtns);
    }

    private void updateSongTitleAndArtist(Song song) {
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());
    }

    private void enablePauseButtonDisablePlayButton() {
        JButton playButton = (JButton) playbackBtns.getComponent(1);
        JButton pauseButton = (JButton) playbackBtns.getComponent(2);

        playButton.setVisible(false);
        playButton.setEnabled(false);

        pauseButton.setVisible(true);
        pauseButton.setEnabled(true);
    }

    private void enablePlayButtonDisablePauseButton() {
        JButton playButton = (JButton) playbackBtns.getComponent(1);
        JButton pauseButton = (JButton) playbackBtns.getComponent(2);

        playButton.setVisible(true);
        playButton.setEnabled(true);

        pauseButton.setVisible(false);
        pauseButton.setEnabled(false);
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