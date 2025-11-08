package br.unifafire.connectnutri.tela;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {

    // --- Novas Constantes de Cores (Baseado no design anexo) ---
    private static final Color COR_FUNDO_PRINCIPAL = new Color(0, 169, 168); // Verde Água/Ciano
    private static final Color COR_DESTAQUE = new Color(236, 64, 122); // Rosa/Magenta (Usada no hover/botão principal)
    private static final Font FONTE_PADRAO = new Font("Verdana", Font.PLAIN, 14); // Fonte Verdana

    public TelaLogin() {
        setTitle("Login - ConnectNutri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        // Painel principal para a cor de fundo (simulando a cor da área não focada)
        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(COR_FUNDO_PRINCIPAL); // Usa a cor de fundo vibrante
        add(painelFundo);

        // Painel branco central (o container do formulário)
        JPanel painelLogin = criarPainelFormularioCentral();
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(new EmptyBorder(30, 40, 30, 40));
        painelLogin.setOpaque(false);

        // --- Logo / Título ---
        // Se você tiver uma imagem de logo clara, ela se destacará melhor
        ImageIcon icon = new ImageIcon("C:\\caminho\\para\\seu\\logo.png");
        JLabel logo = new JLabel(icon, SwingConstants.CENTER);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelLogin.add(logo);
        painelLogin.add(Box.createVerticalStrut(20));

        // --- Campos Email e Senha (Estilização da Verdana e Tamanho) ---
        JTextField campoEmail = criarCampoTextoEstilizado("Email");
        painelLogin.add(campoEmail);
        painelLogin.add(Box.createVerticalStrut(10));

        JPasswordField campoSenha = criarCampoSenhaEstilizado("Senha");
        painelLogin.add(campoSenha);
        painelLogin.add(Box.createVerticalStrut(30));

        // Linha de opções
        JPanel opcoes = new JPanel(new BorderLayout());
        opcoes.setOpaque(false);
        JCheckBox lembrar = new JCheckBox("Relembrar por 30 dias");
        lembrar.setOpaque(false);
        lembrar.setFont(FONTE_PADRAO);
        JLabel esqueceu = new JLabel("Esqueceu a senha?");
        esqueceu.setFont(FONTE_PADRAO.deriveFont(Font.PLAIN, 12));
        opcoes.add(lembrar, BorderLayout.WEST);
        opcoes.add(esqueceu, BorderLayout.EAST);
        painelLogin.add(opcoes);
        painelLogin.add(Box.createVerticalStrut(25));

        // --- Botões estilizados com Hover e Borda Arredondada (Destaque Rosa) ---

        // Botão Login
        JButton btnLogin = new JButton("Login");
        estilizarBotaoComHover(btnLogin, COR_DESTAQUE, COR_FUNDO_PRINCIPAL); // Destaque Rosa
        painelLogin.add(btnLogin);
        painelLogin.add(Box.createVerticalStrut(10));

        // Botão Cadastre-se
        JButton btnCadastro = new JButton("Cadastre-se");
        estilizarBotaoComHover(btnCadastro, COR_DESTAQUE, COR_FUNDO_PRINCIPAL); // Destaque Rosa

        btnCadastro.addActionListener(e -> {
            this.dispose();
            new CadastroPaciente();
        });

        painelLogin.add(btnCadastro);
        painelLogin.add(Box.createVerticalStrut(10));

        // Botão Login com E-mail (Estilo alternativo)
        JButton btnGoogle = new JButton("Login com e-mail");
        estilizarBotaoComHover(btnGoogle, new Color(240, 240, 240), COR_DESTAQUE); // Fundo Cinza/Branco
        painelLogin.add(btnGoogle);

        painelFundo.add(painelLogin);
        setVisible(true);
    }

    // --- Métodos Auxiliares de Estilização ---

    // 1. Painel central com bordas arredondadas
    private JPanel criarPainelFormularioCentral() {
        JPanel painelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); // Bordas arredondadas
                g2.dispose();
            }
        };
        painelLogin.setBackground(Color.WHITE);
        painelLogin.setPreferredSize(new Dimension(380, 580));
        return painelLogin;
    }

    // 2. Criação de Campo de Texto estilizado (Verdana, menor)
    private JTextField criarCampoTextoEstilizado(String titulo) {
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(300, 45)); // Altura um pouco maior para toque/facilidade
        campo.setFont(FONTE_PADRAO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return campo;
    }

    // 3. Criação de Campo de Senha estilizado (Verdana, menor)
    private JPasswordField criarCampoSenhaEstilizado(String titulo) {
        JPasswordField campo = new JPasswordField();
        campo.setPreferredSize(new Dimension(300, 45));
        campo.setFont(FONTE_PADRAO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return campo;
    }

    // 4. Estilização de Botões com Borda Arredondada e efeito Hover
    // CorFundoOriginal: Cor que o botão inicia (Rosa para Login/Cadastro, Cinza para Google)
    // CorHover: Cor que o botão assume no hover (Cor de Destaque Inversa ou Fundo Principal)
    private void estilizarBotaoComHover(JButton botao, Color corFundoOriginal, Color corHover) {
        // Estilo padrão
        botao.setBackground(corFundoOriginal);
        botao.setFont(new Font("Verdana", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(300, 45));
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Customização de borda (Simulação de bordas arredondadas)
        botao.setBorder(BorderFactory.createLineBorder(corFundoOriginal, 8, true));
        botao.setBorderPainted(true);

        // Cores do Texto
        if (corFundoOriginal.getRed() > 200 && corFundoOriginal.getGreen() > 200 && corFundoOriginal.getBlue() > 200) {
            botao.setForeground(Color.BLACK); // Texto preto em fundo claro
        } else {
            botao.setForeground(Color.WHITE); // Texto branco em fundo escuro/rosa
        }

        // Efeito Hover
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Ao passar o mouse, usa a cor de hover
                botao.setBackground(corHover);
                botao.setForeground(Color.WHITE);
                botao.setBorder(BorderFactory.createLineBorder(corHover, 8, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Retorna à cor original
                botao.setBackground(corFundoOriginal);
                botao.setBorder(BorderFactory.createLineBorder(corFundoOriginal, 8, true));

                if (corFundoOriginal.getRed() > 200 && corFundoOriginal.getGreen() > 200 && corFundoOriginal.getBlue() > 200) {
                    botao.setForeground(Color.BLACK);
                } else {
                    botao.setForeground(Color.WHITE);
                }
            }
        });
    }
}