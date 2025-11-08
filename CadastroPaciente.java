package br.unifafire.connectnutri.tela;

import br.unifafire.connectnutri.modelo.Paciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroPaciente extends JFrame {

    private static final Color COR_FUNDO_PRINCIPAL = new Color(0, 169, 168);
    private static final Color COR_DESTAQUE = new Color(236, 64, 122);
    private static final Color COR_HOVER = new Color(0, 192, 192);
    private static final Font FONTE_PADRAO_CAMPO = new Font("Verdana", Font.PLAIN, 14);
    private static final Font FONTE_PADRAO_BOTAO = new Font("Verdana", Font.BOLD, 16);

    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoDataNascimento;
    private JTextField campoEmail;
    private JTextArea areaMotivoConsulta;

    public CadastroPaciente() {
        setTitle("ConnectNutri - Cadastro de Paciente");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelFundo = criarPainelFundoConsistente();

        JPanel painelFormulario = criarPainelFormulario();

        adicionarComponentes(painelFormulario);

        painelFundo.add(painelFormulario);
        add(painelFundo);
        setVisible(true);
    }

    private JPanel criarPainelFundoConsistente() {
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(COR_FUNDO_PRINCIPAL);
        painelFundo.setLayout(new GridBagLayout()); // Centraliza o painel interno
        return painelFundo;
    }

    private JPanel criarPainelFormulario() {
        JPanel painelFormulario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setPreferredSize(new Dimension(600, 800)); // Ajustado para o formulário
        painelFormulario.setLayout(new GridBagLayout());
        painelFormulario.setOpaque(false);
        return painelFormulario;
    }

    private void adicionarComponentes(JPanel painelFormulario) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel titulo = new JLabel("Cadastro de Novo Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Verdana", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        painelFormulario.add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1;
        campoNome = criarCampoTexto();
        painelFormulario.add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painelFormulario.add(new JLabel("Telefone (WhatsApp):"), gbc);
        gbc.gridx = 1;
        campoTelefone = criarCampoTexto();
        painelFormulario.add(campoTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painelFormulario.add(new JLabel("Data de Nasc. (DD/MM/AAAA):"), gbc);
        gbc.gridx = 1;
        campoDataNascimento = criarCampoTexto();
        painelFormulario.add(campoDataNascimento, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painelFormulario.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        campoEmail = criarCampoTexto();
        painelFormulario.add(campoEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        painelFormulario.add(new JLabel("Motivo da Consulta:"), gbc);

        gbc.gridy++;
        areaMotivoConsulta = new JTextArea(5, 20);
        areaMotivoConsulta.setFont(FONTE_PADRAO_CAMPO);
        areaMotivoConsulta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(areaMotivoConsulta);
        painelFormulario.add(scrollPane, gbc);

        gbc.insets = new Insets(30, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Ocupa a largura total da coluna de campos
        gbc.fill = GridBagConstraints.NONE; // Não expande
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza

        // Botão Cadastrar (Rosa/Magenta)
        gbc.gridy++;
        JButton btnCadastrar = new JButton("Confirmar Cadastro");
        estilizarBotaoComHover(btnCadastrar, COR_DESTAQUE, COR_HOVER);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPaciente();
            }
        });
        painelFormulario.add(btnCadastrar, gbc);

        // Botão Voltar (Estilo alternativo - Cinza)
        gbc.gridy++;
        JButton btnVoltar = new JButton("Voltar ao Login");
        // Usando uma cor secundária cinza para o botão de Voltar
        estilizarBotaoComHover(btnVoltar, new Color(153, 153, 153), new Color(170, 170, 170));
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });
        painelFormulario.add(btnVoltar, gbc);
    }

    // Método auxiliar para criar campos de texto padronizados (Verdana)
    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(20);
        campo.setPreferredSize(new Dimension(250, 35));
        campo.setFont(FONTE_PADRAO_CAMPO);
        return campo;
    }

    // Método de Estilização de Botões (Padronizado e centralizado)
    private void estilizarBotaoComHover(JButton botao, Color corFundoOriginal, Color corHover) {
        // Padrão de tamanho e fonte
        botao.setFont(FONTE_PADRAO_BOTAO);
        botao.setPreferredSize(new Dimension(300, 45)); // Tamanho Padrão (300x45)

        // Estilo de bordas arredondadas e cor inicial
        botao.setBackground(corFundoOriginal);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Borda Arredondada (Consistente com TelaLogin)
        botao.setBorder(BorderFactory.createLineBorder(corFundoOriginal, 8, true));
        botao.setBorderPainted(true);

        // Cor do Texto
        if (corFundoOriginal.getRed() > 200 && corFundoOriginal.getGreen() > 200 && corFundoOriginal.getBlue() > 200) {
            botao.setForeground(Color.BLACK);
        } else {
            botao.setForeground(Color.WHITE);
        }

        // Efeito Hover
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(corHover);
                botao.setForeground(Color.WHITE);
                botao.setBorder(BorderFactory.createLineBorder(corHover, 8, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
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


    // Lógica de Validação e Cadastro (Mantida do código anterior)
    private void cadastrarPaciente() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String dataNascStr = campoDataNascimento.getText().trim();
        String email = campoEmail.getText().trim();
        String motivo = areaMotivoConsulta.getText().trim();
        LocalDate dataNascimento = null;
        boolean valido = true;

        // ... (Validações de campos) ...
        if (nome.isEmpty() || telefone.isEmpty() || dataNascStr.isEmpty() || email.isEmpty() || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            valido = false;
        }

        if (valido && !Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um endereço de e-mail válido.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            valido = false;
        }

        if (valido) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataNascimento = LocalDate.parse(dataNascStr, formatter);
                if (dataNascimento.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(this, "A data de nascimento não pode ser futura.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    valido = false;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido. Use DD/MM/AAAA.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                valido = false;
            }
        }

        if (valido) {
            new Paciente(nome, telefone, dataNascimento, email, motivo);
            System.out.println("Paciente Cadastrado (Ainda sem persistência de dados): " + nome);

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!\nProssiga para o agendamento de sua consulta.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Próxima Ação: Abrir a tela de Agendamento (RF002)
            dispose();
            // Ex: new TelaAgendamento();
        }
    }
}