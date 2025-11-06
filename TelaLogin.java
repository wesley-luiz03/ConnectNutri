import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Login - ConnectNutri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Tela cheia
        setUndecorated(false); // Muda para true se quiser ocultar a barra de título
        setResizable(false);

        // Painel principal com fundo em degradê
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint grad = new GradientPaint(
                        0, 0, new Color(139, 230, 104),   // verde mais escuro
                        getWidth(), getHeight(), new Color(198, 255, 145) // verde mais claro
                );
                g2d.setPaint(grad);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelFundo.setLayout(new GridBagLayout());
        add(painelFundo);

        // Painel branco central
        JPanel painelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Cor de fundo e bordas arredondadas
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); // ← raio 40px
                g2.dispose();
            }
        };

        painelLogin.setBackground(Color.WHITE);
        painelLogin.setPreferredSize(new Dimension(450, 650));
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(new EmptyBorder(30, 40, 30, 40));
        painelLogin.setOpaque(false);
        // Logo / Título (usando imagem)
        ImageIcon icon = new ImageIcon("C:\\Users\\UniFAFIRE012\\Downloads\\image.png");

        // Redimensiona a imagem para caber melhor no painel
        Image img = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        // Cria o JLabel com a imagem
        JLabel logo = new JLabel(icon, SwingConstants.CENTER);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelLogin.add(logo);
        painelLogin.add(Box.createVerticalStrut(20));

        // Campo Email
        JTextField campoEmail = new JTextField();
        campoEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        campoEmail.setColumns(58);
        painelLogin.add(campoEmail);
        painelLogin.add(Box.createVerticalStrut(10));

        // Campo Senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBorder(BorderFactory.createTitledBorder("Senha"));
        painelLogin.add(campoSenha);
        painelLogin.add(Box.createVerticalStrut(30));

        // Linha de opções
        JPanel opcoes = new JPanel(new BorderLayout());
        opcoes.setOpaque(false);
        JCheckBox lembrar = new JCheckBox("Relembrar por 30 dias");
        lembrar.setOpaque(false);
        JLabel esqueceu = new JLabel("Esqueceu a senha?");
        esqueceu.setFont(new Font("SansSerif", Font.PLAIN, 12));
        opcoes.add(lembrar, BorderLayout.WEST);
        opcoes.add(esqueceu, BorderLayout.EAST);
        painelLogin.add(opcoes);
        painelLogin.add(Box.createVerticalStrut(25));

        // Botões principais
        JButton btnLogin = new JButton("Login");
        estilizarBotao(btnLogin, Color.decode("#908d8e"), Color.WHITE);
        painelLogin.add(btnLogin);
        painelLogin.add(Box.createVerticalStrut(10));
        // btnLogin.setBorder(new javax.swing.border.LineBorder(Color.decode("#ff97cf"), 8, true));

        JButton btnCadastro = new JButton("Cadastre-se");
        estilizarBotao(btnCadastro, Color.decode("#908d8e"), Color.WHITE);
        painelLogin.add(btnCadastro);
        painelLogin.add(Box.createVerticalStrut(10));

        JButton btnGoogle = new JButton("Login com e-mail");
        estilizarBotao(btnGoogle, new Color(240, 240, 240), Color.BLACK);
        painelLogin.add(btnGoogle);

        painelFundo.add(painelLogin);
    }

    // Método auxiliar para estilizar botões

    private void estilizarBotao(JButton botao, Color fundo, Color texto) {
        botao.setBackground(fundo);
        botao.setForeground(texto);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setPreferredSize(new Dimension(260, 40));
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        });
    }
}
