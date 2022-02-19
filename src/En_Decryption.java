import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import org.apache.commons.codec.binary.Base64;

import AES.aes_lib;
import DES.Decryption;
import DES.Encryption;
import DES.mainDES;
import DH.AESUtil;
import DH.Base64Utils;
import DH.DH;
import MD5.MD5;
import Monoalphabetic.Affine;
import Monoalphabetic.KeywordCipher;
import Monoalphabetic.MultiliteralCipher;
import Monoalphabetic.TestCaesar;
import PerutationCipher.DoubleTransposionCipher;
import PerutationCipher.Permutation;
import PerutationCipher.column_permutation;
import Polyalphabetic.AutokeyCipher;
import Polyalphabetic.AutokeyPlaintext;
import Polyalphabetic.vigenere;
import Polygraphic.TestPlayFair;
import PublicKeyCipher.RSA;
import StreamCipher.CA_2D;
import StreamCipher.RC4;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class En_Decryption {

	private JFrame frame;
	private JTextField textField_Caesar_Str;
	private JTextField textField_Caesar_key;
	private JTextArea textArea_Caesar;
	private JTextArea textArea_Keyword;
	private JTextArea textArea_Mutiliteral;
	private JTextArea textArea_RC4;
	private JTextArea textArea_RSA;
	private JTextArea textArea_Vigenere;
	private JTextArea textArea_Autokey_C;
	private JTextArea textArea_Autokey_P;
	private JTextArea textArea_Playfair;
	private JTextArea textArea_Permutation;
	private JTextArea textArea_DoubleTransposition;
	private JTextArea textArea_ColumnPermutation;
	private JTextArea textArea_DES;
	private JTextArea textArea_Affine;
	
	private JTextField textField_Keyword_Str;
	private JTextField textField_keyword_Key;
	private JTextField textField_Multiliteral_Str;
	private JTextField textField_Multiliteral_Key;
	
	private JTextField textField_RC4_Str;
	private JTextField textField_RC4_Key;
	private JTextField textField_CA_X;
	private JTextField textField_CA_Y;
	private JTextField textField_CA_bits;
	private JTextField textField_CA_key;
	private JTextField textField_RSA_Str;
	private JTextField textField_RSA_Key;
	private JTextField textField_RSA_PK;
	private JTextField textField_RSA_SK;
	private JTextField textField_RSA_N;
	private JTextField textField_RSA_GetN;
	private JTextField textField_MD5_Message;
	private JTextField textField_MD5_Digit;
	private JTextField textField_MD5_Digit_Up;
	private JTextField textField_DH_A_PK;
	private JTextField textField_DH_A_SK;
	private JTextField textField_DH_B_PK;
	private JTextField textField_DH_B_SK;
	private JTextField textField_DH_A_Local;
	private JTextField textField_DH_B_Local;
	private JTextField textField_DH_A_Send;
	private JTextField textField_DH_A_local_EN;
	private JTextField textField_DH_B_DE;
	private JTextField textField_DH_B_Send;
	private JTextField textField_DH_B_EN;
	private JTextField textField_DH_A_DE;
	private JTextField textField_Vigenere_Str;
	private JTextField textField_Vigenere_Key;
	private JTextField textField_Autokey_C_Str;
	private JTextField textField_Autokey_C_Key;
	private JTextField textField_Autokey_P_Str;
	private JTextField textField_Autokey_P_Key;
	private JTextField textField_Playfair_Str;
	private JTextField textField_Playfair_Key;
	private JTextField textField_Permutation_Str;
	private JTextField textField_Permutation_Key;
	private JTextField textField_ColumnPermutation_Str;
	private JTextField textField_ColumnPermutation_Key;
	private JTextField textField_DoubleTransposition_Str;
	private JTextField textField_DoubleTransposition_Key1;
	private JTextField textField_Affine_Str;
	private JTextField textField_Affine_Key1;
	private JTextField textField_Affine_Key2;
	private JTextField textField_DES_Str;
	private JTextField textField_DES_Key;
	private JTextField textField_DoubleTransposition_Key2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					En_Decryption window = new En_Decryption();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public En_Decryption() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public String readFile(){//读文件
		java.io.File file;
        final JFileChooser fileDialog = new JFileChooser();
        int returnVal = fileDialog.showOpenDialog(fileDialog);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileDialog.getSelectedFile();
            file.getName();
        } else {
        	file = null;
        }
        BufferedReader bReader;
        StringBuffer sBuffer=new StringBuffer();
        try {
            bReader=new BufferedReader(new FileReader(file));
            String str;
            while((str=bReader.readLine())!=null){
                sBuffer.append(str+'\n');
                System.out.println(str);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return sBuffer.toString();
    }
	
	private void initialize() {
		CardLayout cardLayout = new CardLayout();
		frame = new JFrame();
		frame.setTitle("单机加密系统");
		frame.setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 872, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(204, 204, 255));
		frame.setJMenuBar(menuBar);
		
		JMenu menu_Mono = new JMenu("单表加密");
		menu_Mono.setBackground(SystemColor.menu);
		menuBar.add(menu_Mono);
		
		JMenuItem menuItem_Caesar = new JMenuItem("Caesar Cipher");
		menu_Mono.add(menuItem_Caesar);
		
		JMenuItem menuItem_Keyword = new JMenuItem("Keyword Cipher");
		menu_Mono.add(menuItem_Keyword);
		
		JMenuItem menuItem_Affine = new JMenuItem("Affine Cipher");
		menu_Mono.add(menuItem_Affine);
		
		JMenuItem menuItem_Multiliteral = new JMenuItem("Multiliteral Cipher");
		menu_Mono.add(menuItem_Multiliteral);
		
		JMenu menu_Poly = new JMenu("多表加密");
		menuBar.add(menu_Poly);
		
		JMenuItem menuItem_Vigenere = new JMenuItem("Vigenere Cipher");
		menu_Poly.add(menuItem_Vigenere);
		
		JMenuItem menuItem_AutokeyCiphertext = new JMenuItem("Autokey Ciphertext");
		menu_Poly.add(menuItem_AutokeyCiphertext);
		
		JMenuItem menuItem_AutokeyPlaintext = new JMenuItem("Autokey Plaintext");
		menu_Poly.add(menuItem_AutokeyPlaintext);
		
		JMenu menu_Polygraphic = new JMenu("多图替代密码");
		menuBar.add(menu_Polygraphic);
		
		JMenuItem menuItem_Playfair = new JMenuItem("Playfair");
		menu_Polygraphic.add(menuItem_Playfair);
		
		JMenu menu_Permutation = new JMenu("置换密码");
		menuBar.add(menu_Permutation);
		
		JMenuItem menuItem_PermutationCipher = new JMenuItem("Permutation Cipher");
		menu_Permutation.add(menuItem_PermutationCipher);
		
		JMenuItem menuItem_Column = new JMenuItem("Column Permutation");
		menu_Permutation.add(menuItem_Column);
		
		JMenuItem menuItem_DoubleTransposition = new JMenuItem("Double Transposition");
		menu_Permutation.add(menuItem_DoubleTransposition);
		
		JMenu menu_Stream = new JMenu("流密码");
		menu_Stream.setBackground(SystemColor.menu);
		menuBar.add(menu_Stream);
		
		JMenuItem menuItem_RC4 = new JMenuItem("RC4");
		menu_Stream.add(menuItem_RC4);
		
		JMenuItem menuItem_CA = new JMenuItem("CA");
		menu_Stream.add(menuItem_CA);
		
		JMenu menu_BlockCipher = new JMenu("分块密码");
		menuBar.add(menu_BlockCipher);
		
		JMenuItem menuItem_DES = new JMenuItem("DES");
		menu_BlockCipher.add(menuItem_DES);
		
		JMenu menu_PK = new JMenu("公钥密码");
		menuBar.add(menu_PK);
		
		JMenuItem menuItem_RSA = new JMenuItem("RSA");
		menu_PK.add(menuItem_RSA);
		
		JMenu menu_MD5 = new JMenu("单向散列函数");
		menuBar.add(menu_MD5);
		
		JMenuItem menuItem_MD5 = new JMenuItem("MD5");
		menu_MD5.add(menuItem_MD5);
		
		JMenu menu_DH = new JMenu("密钥交换");
		menuBar.add(menu_DH);
		
		JMenuItem menuItem_DH = new JMenuItem("DH");
		menu_DH.add(menuItem_DH);
		
		//添加布局管理器
		frame.getContentPane().setLayout(cardLayout);
		
		//添加页面容器
//Caesar页面
		JPanel panel_Caesar = new JPanel();
		panel_Caesar.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Caesar, "Caesar");
				
		textField_Caesar_key = new JTextField();
		textField_Caesar_key.setBounds(233, 92, 286, 41);
		textField_Caesar_key.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_Caesar.setLayout(null);
		textField_Caesar_Str = new JTextField();
		textField_Caesar_Str.setBounds(233, 35, 286, 41);
		textField_Caesar_Str.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_Caesar.add(textField_Caesar_Str);
		textField_Caesar_Str.setColumns(10);
		
		JButton btn_Caesar_En = new JButton("加密");
		btn_Caesar_En.setBounds(656, 39, 163, 37);
		btn_Caesar_En.setBackground(new Color(153, 204, 153));
		btn_Caesar_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestCaesar c = new TestCaesar();
				String pt = textField_Caesar_Str.getText();
				int key = 0;
				try{
					key = Integer.valueOf(textField_Caesar_key.getText());	
				}catch(Exception e) {
					textArea_Caesar.setText("输入key非法！");
				}
				String result = c.encrypt(pt, key);
				textArea_Caesar.setText(result);
			}
		});
		panel_Caesar.add(btn_Caesar_En);
		
		JButton btn_Caesar_De = new JButton("解密");
		btn_Caesar_De.setBounds(656, 96, 163, 37);
		btn_Caesar_De.setBackground(new Color(204, 255, 255));
		btn_Caesar_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestCaesar c = new TestCaesar();
				String ct = textField_Caesar_Str.getText();
				int key = 0;
				try{
					key = Integer.valueOf(textField_Caesar_key.getText());	
				}catch(Exception e_caesar) {
					textArea_Caesar.setText("输入key非法！");
				}
				String result = c.decrypt(ct, key);
				textArea_Caesar.setText(result);
			}
		});
		panel_Caesar.add(btn_Caesar_De);
		panel_Caesar.add(textField_Caesar_key);
		textField_Caesar_key.setColumns(10);
		
		JLabel lbl_Caesar_1 = new JLabel("请输入英文字符串：");
		lbl_Caesar_1.setBounds(10, 47, 225, 30);
		lbl_Caesar_1.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_Caesar.add(lbl_Caesar_1);
		
		JLabel lbl_Caesar_2 = new JLabel("请输入密钥：");
		lbl_Caesar_2.setBounds(10, 101, 216, 29);
		lbl_Caesar_2.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_Caesar.add(lbl_Caesar_2);
		
		JLabel lbl_Caesar_3 = new JLabel("结果：");
		lbl_Caesar_3.setBounds(10, 187, 75, 30);
		lbl_Caesar_3.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_Caesar.add(lbl_Caesar_3);
		
		JScrollPane scrollPane_Caesar = new JScrollPane();
		scrollPane_Caesar.setBounds(47, 235, 751, 117);
		panel_Caesar.add(scrollPane_Caesar);
		
		textArea_Caesar = new JTextArea();
		scrollPane_Caesar.setViewportView(textArea_Caesar);
		textArea_Caesar.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Caesar.setWrapStyleWord(true);		// 激活断行不断字功能
		textArea_Caesar.setFont(new Font("宋体", Font.PLAIN, 25));
		
		JLabel lbl_Caesar = new JLabel("凯撒密码");
		lbl_Caesar.setBounds(10, 0, 168, 47);
		lbl_Caesar.setFont(new Font("宋体", Font.BOLD, 40));
		panel_Caesar.add(lbl_Caesar);
		
		JButton btnNewButton = new JButton("打开文件");
		btnNewButton.setBackground(new Color(255, 204, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = readFile();
				textField_Caesar_Str.setText(str);
			}
		});
		btnNewButton.setBounds(656, 157, 163, 37);
		panel_Caesar.add(btnNewButton);
		
//Keyword页面
		JPanel panel_Keyword = new JPanel();
		panel_Keyword.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Keyword, "Keyword");
		SpringLayout sl_panel_Keyword = new SpringLayout();
		panel_Keyword.setLayout(sl_panel_Keyword);
		
		JButton btn_Keyword_En = new JButton("解密");
		btn_Keyword_En.setBackground(new Color(204, 255, 255));
		btn_Keyword_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeywordCipher play = new KeywordCipher();
				String result = "";
				String cipherText= "";
				String key = "";
				try {
					try {
						cipherText = textField_Keyword_Str.getText();
						key = textField_keyword_Key.getText();
					}catch(Exception e_keyword1) {
						textArea_Keyword.setText("Invalid！");
					}
					result = play.decrypt(cipherText, key);
				}catch(Exception e_keyword) {
					textArea_Keyword.setText("Invalid！");
				}
				textArea_Keyword.setText(result);
			}
		});
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, btn_Keyword_En, -45, SpringLayout.EAST, panel_Keyword);
		panel_Keyword.add(btn_Keyword_En);
		
		JButton btn_Keyword_De = new JButton("加密");
		btn_Keyword_De.setBackground(new Color(153, 204, 153));
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, btn_Keyword_De, -45, SpringLayout.EAST, panel_Keyword);
		btn_Keyword_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeywordCipher play = new KeywordCipher();
				String result = "";
				String plainText= "";
				String key = "";
				try {
					try {
						plainText = textField_Keyword_Str.getText();
						key = textField_keyword_Key.getText();
					}catch(Exception e_keyword1) {
						textArea_Keyword.setText("Invalid！");
					}
					result = play.encrypt(plainText, key);
				}catch(Exception e_keyword) {
					textArea_Keyword.setText("Invalid！");
				}
				textArea_Keyword.setText(result);
			}
		});
		panel_Keyword.add(btn_Keyword_De);
		
		JLabel lbl_Keyword_1 = new JLabel("请输入英文字符：");
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, btn_Keyword_De, 0, SpringLayout.NORTH, lbl_Keyword_1);
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, btn_Keyword_De, 37, SpringLayout.NORTH, lbl_Keyword_1);
		lbl_Keyword_1.setFont(new Font("宋体", Font.PLAIN, 24));
		panel_Keyword.add(lbl_Keyword_1);
		
		JLabel lbl_Keyword_2 = new JLabel("请输入密钥：");
		lbl_Keyword_2.setFont(new Font("宋体", Font.PLAIN, 24));
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, lbl_Keyword_2, 50, SpringLayout.SOUTH, lbl_Keyword_1);
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, lbl_Keyword_2, 192, SpringLayout.WEST, lbl_Keyword_1);
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, lbl_Keyword_2, 21, SpringLayout.SOUTH, lbl_Keyword_1);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, lbl_Keyword_2, 0, SpringLayout.WEST, lbl_Keyword_1);
		panel_Keyword.add(lbl_Keyword_2);
		
		JLabel lbl_Keyword_3 = new JLabel("结果");
		lbl_Keyword_3.setFont(new Font("宋体", Font.PLAIN, 24));
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, lbl_Keyword_3, 28, SpringLayout.SOUTH, lbl_Keyword_2);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, lbl_Keyword_3, 0, SpringLayout.WEST, lbl_Keyword_1);
		panel_Keyword.add(lbl_Keyword_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, scrollPane_1, 119, SpringLayout.NORTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, scrollPane_1, 481, SpringLayout.WEST, panel_Keyword);
		panel_Keyword.add(scrollPane_1);
		
		textField_Keyword_Str = new JTextField();
		textField_Keyword_Str.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, btn_Keyword_De, 140, SpringLayout.EAST, textField_Keyword_Str);
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, textField_Keyword_Str, 63, SpringLayout.NORTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, textField_Keyword_Str, 45, SpringLayout.EAST, lbl_Keyword_1);
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, textField_Keyword_Str, -281, SpringLayout.EAST, panel_Keyword);
		panel_Keyword.add(textField_Keyword_Str);
		textField_Keyword_Str.setColumns(10);
		
		textField_keyword_Key = new JTextField();
		textField_keyword_Key.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, btn_Keyword_En, 2, SpringLayout.NORTH, textField_keyword_Key);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, btn_Keyword_En, 140, SpringLayout.EAST, textField_keyword_Key);
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, btn_Keyword_En, 39, SpringLayout.NORTH, textField_keyword_Key);
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, textField_keyword_Key, 123, SpringLayout.NORTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, textField_Keyword_Str, -18, SpringLayout.NORTH, textField_keyword_Key);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, textField_keyword_Key, 0, SpringLayout.WEST, textField_Keyword_Str);
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, textField_keyword_Key, -281, SpringLayout.EAST, panel_Keyword);
		textField_keyword_Key.setColumns(10);
		panel_Keyword.add(textField_keyword_Key);
		
		JScrollPane scrollPane_Keyword = new JScrollPane();
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, textField_keyword_Key, -41, SpringLayout.NORTH, scrollPane_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, scrollPane_Keyword, 206, SpringLayout.NORTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, scrollPane_Keyword, -30, SpringLayout.SOUTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, scrollPane_Keyword, 110, SpringLayout.WEST, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, scrollPane_Keyword, 652, SpringLayout.WEST, panel_Keyword);
		panel_Keyword.add(scrollPane_Keyword);
		
		textArea_Keyword = new JTextArea();
		textArea_Keyword.setFont(new Font("宋体", Font.PLAIN, 25));
		textArea_Keyword.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Keyword.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Keyword.setViewportView(textArea_Keyword);
		
		JLabel lbl_keyword = new JLabel("关键字密码");
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, lbl_Keyword_1, 22, SpringLayout.SOUTH, lbl_keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, lbl_Keyword_1, 0, SpringLayout.WEST, lbl_keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, lbl_keyword, 0, SpringLayout.NORTH, panel_Keyword);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, lbl_keyword, 10, SpringLayout.WEST, panel_Keyword);
		lbl_keyword.setFont(new Font("宋体", Font.BOLD, 40));
		panel_Keyword.add(lbl_keyword);
		
		JButton btnNewButton_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = readFile();
				textField_Keyword_Str.setText(str);
			}
		});
		sl_panel_Keyword.putConstraint(SpringLayout.NORTH, btnNewButton_1, 23, SpringLayout.SOUTH, btn_Keyword_En);
		sl_panel_Keyword.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, btn_Keyword_En);
		sl_panel_Keyword.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 60, SpringLayout.SOUTH, btn_Keyword_En);
		sl_panel_Keyword.putConstraint(SpringLayout.EAST, btnNewButton_1, 96, SpringLayout.WEST, btn_Keyword_En);
		btnNewButton_1.setBackground(new Color(255, 204, 153));
		panel_Keyword.add(btnNewButton_1);
	
//Multiliteral页面		
		JPanel panel_Multiliteral = new JPanel();
		panel_Multiliteral.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Multiliteral, "Multiliteral");
		SpringLayout sl_panel_Multiliteral = new SpringLayout();
		panel_Multiliteral.setLayout(sl_panel_Multiliteral);
		
		JLabel lbl_Multiliteral = new JLabel("Multiliteral Cipher");
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, lbl_Multiliteral, 0, SpringLayout.NORTH, panel_Multiliteral);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, lbl_Multiliteral, 0, SpringLayout.WEST, panel_Multiliteral);
		lbl_Multiliteral.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		panel_Multiliteral.add(lbl_Multiliteral);
		
		JLabel lbl_Multiliteral_1 = new JLabel("请输入英文字符串：");
		lbl_Multiliteral_1.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, lbl_Multiliteral_1, 16, SpringLayout.SOUTH, lbl_Multiliteral);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, lbl_Multiliteral_1, 0, SpringLayout.WEST, lbl_Multiliteral);
		panel_Multiliteral.add(lbl_Multiliteral_1);
		
		JLabel lbl_Multiliteral_2 = new JLabel("请输入密钥：");
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, lbl_Multiliteral_2, 0, SpringLayout.WEST, lbl_Multiliteral_1);
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, lbl_Multiliteral_2, 44, SpringLayout.SOUTH, lbl_Multiliteral_1);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, lbl_Multiliteral_2, 225, SpringLayout.WEST, lbl_Multiliteral);
		lbl_Multiliteral_2.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, lbl_Multiliteral_2, 14, SpringLayout.SOUTH, lbl_Multiliteral_1);
		panel_Multiliteral.add(lbl_Multiliteral_2);
		
		textField_Multiliteral_Str = new JTextField();
		textField_Multiliteral_Str.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, textField_Multiliteral_Str, 6, SpringLayout.SOUTH, lbl_Multiliteral);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, textField_Multiliteral_Str, 36, SpringLayout.EAST, lbl_Multiliteral_1);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, textField_Multiliteral_Str, -241, SpringLayout.EAST, panel_Multiliteral);
		panel_Multiliteral.add(textField_Multiliteral_Str);
		textField_Multiliteral_Str.setColumns(10);
		
		textField_Multiliteral_Key = new JTextField();
		textField_Multiliteral_Key.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, textField_Multiliteral_Key, 0, SpringLayout.WEST, textField_Multiliteral_Str);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, textField_Multiliteral_Key, -241, SpringLayout.EAST, panel_Multiliteral);
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, textField_Multiliteral_Str, -5, SpringLayout.NORTH, textField_Multiliteral_Key);
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, textField_Multiliteral_Key, 7, SpringLayout.SOUTH, lbl_Multiliteral_2);
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, textField_Multiliteral_Key, -6, SpringLayout.NORTH, lbl_Multiliteral_2);
		textField_Multiliteral_Key.setColumns(10);
		panel_Multiliteral.add(textField_Multiliteral_Key);
		
		JButton btn_Multiliteral_En = new JButton("加密");
		btn_Multiliteral_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiliteralCipher play = new MultiliteralCipher();
				String plainText = textField_Multiliteral_Str.getText();
				String key = textField_Multiliteral_Key.getText();
				String result = "";
				try {
					result = play.encrypt(plainText, key);
					textArea_Mutiliteral.setText(result);
				}catch(Exception e_Multiliteral_1) {
					textArea_Mutiliteral.setText("Invalid！");
				}
			}
		});
		btn_Multiliteral_En.setBackground(new Color(153, 204, 153));
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, btn_Multiliteral_En, 0, SpringLayout.SOUTH, lbl_Multiliteral_1);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, btn_Multiliteral_En, -27, SpringLayout.EAST, panel_Multiliteral);
		panel_Multiliteral.add(btn_Multiliteral_En);
		
		JButton btn_Multiliteral_De = new JButton("解密");
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, btn_Multiliteral_De, 0, SpringLayout.EAST, btn_Multiliteral_En);
		btn_Multiliteral_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiliteralCipher play = new MultiliteralCipher();
				String cipherText = textField_Multiliteral_Str.getText();
				String key = textField_Multiliteral_Key.getText();
				String result = "";
				try {
					result = play.decrypt(cipherText, key);
					textArea_Mutiliteral.setText(result);
				}catch(Exception e_Multiliteral_2) {
					textArea_Mutiliteral.setText("Invalid！");
				}

			}
		});
		btn_Multiliteral_De.setBackground(new Color(204, 255, 255));
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, btn_Multiliteral_De, 0, SpringLayout.SOUTH, textField_Multiliteral_Key);
		panel_Multiliteral.add(btn_Multiliteral_De);
		
		JLabel lb_Multiliteral_3 = new JLabel("结果：");
		lb_Multiliteral_3.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, lb_Multiliteral_3, 25, SpringLayout.SOUTH, lbl_Multiliteral_2);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, lb_Multiliteral_3, 0, SpringLayout.WEST, lbl_Multiliteral);
		panel_Multiliteral.add(lb_Multiliteral_3);
		
		JScrollPane scrollPane_Multiliteral = new JScrollPane();
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, scrollPane_Multiliteral, 54, SpringLayout.SOUTH, textField_Multiliteral_Key);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, scrollPane_Multiliteral, 10, SpringLayout.WEST, lb_Multiliteral_3);
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, scrollPane_Multiliteral, -5, SpringLayout.SOUTH, panel_Multiliteral);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, scrollPane_Multiliteral, 0, SpringLayout.EAST, btn_Multiliteral_En);
		panel_Multiliteral.add(scrollPane_Multiliteral);
		
		textArea_Mutiliteral = new JTextArea();
		textArea_Mutiliteral.setFont(new Font("宋体", Font.PLAIN, 25));
		textArea_Mutiliteral.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Mutiliteral.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Multiliteral.setViewportView(textArea_Mutiliteral);
		
		JButton btnNewButton_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Multiliteral_Str.setText(str);
			}
		});
		sl_panel_Multiliteral.putConstraint(SpringLayout.NORTH, btnNewButton_1_1, 13, SpringLayout.SOUTH, btn_Multiliteral_De);
		sl_panel_Multiliteral.putConstraint(SpringLayout.WEST, btnNewButton_1_1, -48, SpringLayout.WEST, btn_Multiliteral_En);
		sl_panel_Multiliteral.putConstraint(SpringLayout.SOUTH, btnNewButton_1_1, -4, SpringLayout.SOUTH, lb_Multiliteral_3);
		sl_panel_Multiliteral.putConstraint(SpringLayout.EAST, btnNewButton_1_1, 0, SpringLayout.EAST, btn_Multiliteral_En);
		btnNewButton_1_1.setBackground(new Color(255, 204, 153));
		panel_Multiliteral.add(btnNewButton_1_1);

//RC4页面
		JPanel panel_RC4 = new JPanel();
		panel_RC4.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_RC4, "RC4");
		SpringLayout sl_panel_RC4 = new SpringLayout();
		panel_RC4.setLayout(sl_panel_RC4);
		
		JLabel lbl_RC4 = new JLabel("RC4");
		lbl_RC4.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, lbl_RC4, 0, SpringLayout.NORTH, panel_RC4);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, lbl_RC4, 0, SpringLayout.WEST, panel_RC4);
		panel_RC4.add(lbl_RC4);
		
		JLabel lbl_RC4_Str = new JLabel("加密或解密字符串：");
		sl_panel_RC4.putConstraint(SpringLayout.SOUTH, lbl_RC4_Str, 36, SpringLayout.SOUTH, lbl_RC4);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, lbl_RC4_Str, 225, SpringLayout.WEST, panel_RC4);
		lbl_RC4_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, lbl_RC4_Str, 6, SpringLayout.SOUTH, lbl_RC4);
		panel_RC4.add(lbl_RC4_Str);
		
		JLabel lbl_RC4_Key = new JLabel("输入密钥：");
		sl_panel_RC4.putConstraint(SpringLayout.WEST, lbl_RC4_Str, 0, SpringLayout.WEST, lbl_RC4_Key);
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, lbl_RC4_Key, 23, SpringLayout.SOUTH, lbl_RC4_Str);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, lbl_RC4_Key, 0, SpringLayout.WEST, lbl_RC4);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, lbl_RC4_Key, 225, SpringLayout.WEST, lbl_RC4);
		lbl_RC4_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_RC4.add(lbl_RC4_Key);
		
		textField_RC4_Str = new JTextField();
		sl_panel_RC4.putConstraint(SpringLayout.WEST, textField_RC4_Str, 20, SpringLayout.EAST, lbl_RC4_Str);
		textField_RC4_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_RC4.add(textField_RC4_Str);
		textField_RC4_Str.setColumns(10);
		
		textField_RC4_Key = new JTextField();
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, textField_RC4_Key, 21, SpringLayout.SOUTH, textField_RC4_Str);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, textField_RC4_Key, 20, SpringLayout.EAST, lbl_RC4_Key);
		textField_RC4_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_RC4.add(textField_RC4_Key);
		textField_RC4_Key.setColumns(10);
		
		JButton btn_RC4_En = new JButton("加密");
		btn_RC4_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RC4 rc4 =new RC4();
				String input = textField_RC4_Str.getText();
				String key = textField_RC4_Key.getText();
				String result = "";
				try {
					result = rc4.MyRC4(input, key);
					textArea_RC4.setText(result);	
				}catch(Exception e_RC4) {
					textArea_RC4.setText("Invalid！");
				}

			}
		});
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, textField_RC4_Str, 0, SpringLayout.NORTH, btn_RC4_En);
		sl_panel_RC4.putConstraint(SpringLayout.SOUTH, textField_RC4_Str, 36, SpringLayout.NORTH, btn_RC4_En);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, textField_RC4_Str, -107, SpringLayout.WEST, btn_RC4_En);
		btn_RC4_En.setBackground(new Color(153, 204, 153));
		sl_panel_RC4.putConstraint(SpringLayout.EAST, btn_RC4_En, -10, SpringLayout.EAST, panel_RC4);
		panel_RC4.add(btn_RC4_En);
		
		JButton btn_RC4_De = new JButton("解密");
		sl_panel_RC4.putConstraint(SpringLayout.SOUTH, btn_RC4_En, -23, SpringLayout.NORTH, btn_RC4_De);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, textField_RC4_Key, -107, SpringLayout.WEST, btn_RC4_De);
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, btn_RC4_De, 0, SpringLayout.NORTH, lbl_RC4_Key);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, btn_RC4_De, 0, SpringLayout.WEST, btn_RC4_En);
		btn_RC4_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RC4 rc4 =new RC4();
				String input = textField_RC4_Str.getText();
				String key = textField_RC4_Key.getText();
				String result = "";
				try {
					result = rc4.MyRC4(input, key);
					textArea_RC4.setText(result);
				}catch(Exception e_RC4) {
					textArea_RC4.setText("Invalid！");
				}

			}
		});
		btn_RC4_De.setBackground(new Color(204, 255, 255));
		panel_RC4.add(btn_RC4_De);
		
		JLabel lbl_RC4_1 = new JLabel("结果：");
		lbl_RC4_1.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, lbl_RC4_1, 20, SpringLayout.SOUTH, lbl_RC4_Key);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, lbl_RC4_1, 0, SpringLayout.WEST, lbl_RC4);
		panel_RC4.add(lbl_RC4_1);
		
		JScrollPane scrollPane_RC4 = new JScrollPane();
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, scrollPane_RC4, 6, SpringLayout.SOUTH, lbl_RC4_1);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, scrollPane_RC4, 10, SpringLayout.WEST, panel_RC4);
		sl_panel_RC4.putConstraint(SpringLayout.SOUTH, scrollPane_RC4, -10, SpringLayout.SOUTH, panel_RC4);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, scrollPane_RC4, 0, SpringLayout.EAST, btn_RC4_En);
		panel_RC4.add(scrollPane_RC4);
		
		textArea_RC4 = new JTextArea();
		textArea_RC4.setEditable(false);
		textArea_RC4.setFont(new Font("宋体", Font.PLAIN, 25));
		textArea_RC4.setLineWrap(true);        	// 激活自动换行功能 
		textArea_RC4.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_RC4.setViewportView(textArea_RC4);
		
		JButton btnNewButton_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_RC4_Str.setText(str);
			}
		});
		sl_panel_RC4.putConstraint(SpringLayout.NORTH, btnNewButton_1_1_1, -3, SpringLayout.NORTH, lbl_RC4_1);
		sl_panel_RC4.putConstraint(SpringLayout.WEST, btnNewButton_1_1_1, 0, SpringLayout.WEST, textField_RC4_Str);
		sl_panel_RC4.putConstraint(SpringLayout.EAST, btnNewButton_1_1_1, -472, SpringLayout.EAST, panel_RC4);
		btnNewButton_1_1_1.setBackground(new Color(255, 204, 153));
		panel_RC4.add(btnNewButton_1_1_1);

//CA页面
		JPanel panel_CA = new JPanel();
		panel_CA.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_CA, "CA");
		SpringLayout sl_panel_CA = new SpringLayout();
		panel_CA.setLayout(sl_panel_CA);
		
		JLabel lbl_CA = new JLabel("CA_2D");
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA, 0, SpringLayout.NORTH, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.EAST, lbl_CA, 0, SpringLayout.EAST, panel_CA);
		lbl_CA.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		panel_CA.add(lbl_CA);
		
		JLabel lbl_CA_1 = new JLabel("结果：");
		lbl_CA_1.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_CA.putConstraint(SpringLayout.WEST, lbl_CA_1, 1, SpringLayout.WEST, panel_CA);
		panel_CA.add(lbl_CA_1);
		
		JScrollPane scrollPane_CA = new JScrollPane();
		sl_panel_CA.putConstraint(SpringLayout.NORTH, scrollPane_CA, 6, SpringLayout.SOUTH, lbl_CA_1);
		sl_panel_CA.putConstraint(SpringLayout.WEST, scrollPane_CA, 31, SpringLayout.WEST, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, scrollPane_CA, -10, SpringLayout.SOUTH, panel_CA);
		panel_CA.add(scrollPane_CA);
		
		JTextArea textArea_CA = new JTextArea();
		textArea_CA.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_CA.setLineWrap(true);        	// 激活自动换行功能 
		textArea_CA.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_CA.setViewportView(textArea_CA);
		
		textField_CA_X = new JTextField();
		sl_panel_CA.putConstraint(SpringLayout.NORTH, textField_CA_X, 8, SpringLayout.NORTH, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.EAST, textField_CA_X, -72, SpringLayout.WEST, lbl_CA);
		panel_CA.add(textField_CA_X);
		textField_CA_X.setColumns(10);
		
		textField_CA_Y = new JTextField();
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, textField_CA_X, -6, SpringLayout.NORTH, textField_CA_Y);
		sl_panel_CA.putConstraint(SpringLayout.WEST, textField_CA_Y, 0, SpringLayout.WEST, textField_CA_X);
		sl_panel_CA.putConstraint(SpringLayout.EAST, textField_CA_Y, -72, SpringLayout.WEST, lbl_CA);
		panel_CA.add(textField_CA_Y);
		textField_CA_Y.setColumns(10);
		
		JLabel lbl_CA_X = new JLabel("X位置(0~2):");
		sl_panel_CA.putConstraint(SpringLayout.WEST, textField_CA_X, 147, SpringLayout.EAST, lbl_CA_X);
		lbl_CA_X.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA_X, 9, SpringLayout.NORTH, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.WEST, lbl_CA_X, 1, SpringLayout.WEST, panel_CA);
		panel_CA.add(lbl_CA_X);
		
		JLabel lbl_CA_Y = new JLabel("Y位置(0~2):");
		sl_panel_CA.putConstraint(SpringLayout.NORTH, textField_CA_Y, 2, SpringLayout.NORTH, lbl_CA_Y);
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, textField_CA_Y, 38, SpringLayout.NORTH, lbl_CA_Y);
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA_Y, 9, SpringLayout.SOUTH, lbl_CA_X);
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA_1, 97, SpringLayout.SOUTH, lbl_CA_Y);
		sl_panel_CA.putConstraint(SpringLayout.WEST, lbl_CA_Y, 1, SpringLayout.WEST, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.EAST, lbl_CA_Y, -613, SpringLayout.EAST, panel_CA);
		lbl_CA_Y.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_CA.add(lbl_CA_Y);
		
		JLabel lbl_CA_Bits = new JLabel("生成位数:建议小于99999");
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA_Bits, 100, SpringLayout.NORTH, panel_CA);
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, lbl_CA_Y, -22, SpringLayout.NORTH, lbl_CA_Bits);
		lbl_CA_Bits.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_CA.putConstraint(SpringLayout.WEST, lbl_CA_Bits, 0, SpringLayout.WEST, lbl_CA_1);
		panel_CA.add(lbl_CA_Bits);
		
		textField_CA_bits = new JTextField();
		sl_panel_CA.putConstraint(SpringLayout.NORTH, textField_CA_bits, -3, SpringLayout.NORTH, lbl_CA_Bits);
		sl_panel_CA.putConstraint(SpringLayout.WEST, textField_CA_bits, 0, SpringLayout.WEST, textField_CA_X);
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, textField_CA_bits, 33, SpringLayout.NORTH, lbl_CA_Bits);
		sl_panel_CA.putConstraint(SpringLayout.EAST, textField_CA_bits, 268, SpringLayout.WEST, textField_CA_X);
		panel_CA.add(textField_CA_bits);
		textField_CA_bits.setColumns(10);
		
		JButton btn_CA = new JButton("生成");
		sl_panel_CA.putConstraint(SpringLayout.EAST, scrollPane_CA, 16, SpringLayout.EAST, btn_CA);
		btn_CA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CA_2D ca = new CA_2D();
				int x = 0;
				int y = 0;
				int bits = 0;
				int key = 0;
				String result = "";
				try {
					x = Integer.valueOf(textField_CA_X.getText());
					y = Integer.valueOf(textField_CA_Y.getText());
					key = Integer.valueOf(textField_CA_key.getText());
					bits = Integer.valueOf(textField_CA_bits.getText());
					result = ca.CA(key, x, y, bits);
					textArea_CA.setText(result);
				}catch(Exception e_CA) {
					textArea_CA.setText("Invalid!");
				}

			}
		});
		btn_CA.setBackground(new Color(204, 255, 204));
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, btn_CA, 0, SpringLayout.SOUTH, lbl_CA_1);
		sl_panel_CA.putConstraint(SpringLayout.EAST, btn_CA, -26, SpringLayout.EAST, panel_CA);
		panel_CA.add(btn_CA);
		
		JLabel lbl_CA_key = new JLabel("输入密钥:");
		lbl_CA_key.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_panel_CA.putConstraint(SpringLayout.NORTH, lbl_CA_key, 6, SpringLayout.SOUTH, lbl_CA_Bits);
		sl_panel_CA.putConstraint(SpringLayout.WEST, lbl_CA_key, 0, SpringLayout.WEST, lbl_CA_1);
		panel_CA.add(lbl_CA_key);
		
		textField_CA_key = new JTextField();
		sl_panel_CA.putConstraint(SpringLayout.NORTH, textField_CA_key, 6, SpringLayout.SOUTH, textField_CA_bits);
		sl_panel_CA.putConstraint(SpringLayout.WEST, textField_CA_key, 0, SpringLayout.WEST, textField_CA_X);
		sl_panel_CA.putConstraint(SpringLayout.SOUTH, textField_CA_key, 42, SpringLayout.SOUTH, textField_CA_bits);
		panel_CA.add(textField_CA_key);
		textField_CA_key.setColumns(10);
		
//RSA界面		
		JPanel panel_RSA = new JPanel();
		panel_RSA.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_RSA, "RSA");
		panel_RSA.setLayout(null);
		
		JLabel lbl_RSA = new JLabel("RSA");
		lbl_RSA.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_RSA.setBounds(738, 0, 108, 29);
		panel_RSA.add(lbl_RSA);
		
		JLabel lbl_RSA_Str = new JLabel("输入字符串：");
		lbl_RSA_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_Str.setBounds(0, 0, 161, 29);
		panel_RSA.add(lbl_RSA_Str);
		
		JLabel lbl_RSA_Key = new JLabel("输入密钥：");
		lbl_RSA_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_Key.setBounds(0, 36, 161, 29);
		panel_RSA.add(lbl_RSA_Key);
		
		textField_RSA_Str = new JTextField();
		textField_RSA_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_Str.setBounds(182, 0, 404, 35);
		panel_RSA.add(textField_RSA_Str);
		textField_RSA_Str.setColumns(10);
		
		textField_RSA_Key = new JTextField();
		textField_RSA_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_Key.setBounds(182, 36, 404, 35);
		panel_RSA.add(textField_RSA_Key);
		textField_RSA_Key.setColumns(10);
		
		JButton btn_RSA_En = new JButton("加密");
		btn_RSA_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RSA rsa = new RSA();
					BigInteger e_rsa = new BigInteger(textField_RSA_Key.getText());
					BigInteger n = new BigInteger(textField_RSA_N.getText());
					String M = textField_RSA_Str.getText();
					byte BM[] = Base64.encodeBase64(M.getBytes());
					BigInteger M_10 = new BigInteger(BM);	//将16进制Byte转化为十进制数
					BigInteger C = rsa.encrypt(M_10,e_rsa,n);		//加密后的十进制密文
					String Base64Str = Base64.encodeBase64String(C.toByteArray());
					textArea_RSA.setText(Base64Str);
				}catch(Exception e_RSA) {
					textArea_RSA.setText("Invalid！");
				}
			}
		});
		btn_RSA_En.setBackground(new Color(153, 204, 153));
		btn_RSA_En.setBounds(676, 36, 127, 37);
		panel_RSA.add(btn_RSA_En);
		
		JButton btn_RSA_De = new JButton("解密");
		btn_RSA_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RSA rsa = new RSA();
					BigInteger d = new BigInteger(textField_RSA_Key.getText());
					BigInteger n = new BigInteger(textField_RSA_N.getText());
					String C = textField_RSA_Str.getText();
					byte[] t =Base64.decodeBase64(C);
					BigInteger M = rsa.decrypt(new BigInteger(t),d,n);		//解密后的十进制密文
					byte[] BCtoM = M.toByteArray();	//将10进制密文转化为16进制Byte
					String result = new String (Base64.decodeBase64(new String(BCtoM)));
					textArea_RSA.setText(result);
				}catch(Exception e_RSA) {
					textArea_RSA.setText("Invalid！");
				}
			}
		});
		btn_RSA_De.setBackground(new Color(204, 255, 255));
		btn_RSA_De.setBounds(676, 76, 127, 37);
		panel_RSA.add(btn_RSA_De);
		
		JLabel lbl_RSA_1 = new JLabel("结果：");
		lbl_RSA_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_1.setBounds(0, 231, 108, 29);
		panel_RSA.add(lbl_RSA_1);
		
		JScrollPane scrollPane_RSA = new JScrollPane();
		scrollPane_RSA.setBounds(32, 265, 793, 108);
		panel_RSA.add(scrollPane_RSA);
		
		textArea_RSA = new JTextArea();
		textArea_RSA.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_RSA.setLineWrap(true);        	// 激活自动换行功能 
		textArea_RSA.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_RSA.setViewportView(textArea_RSA);
		
		JLabel lbl_RSA_PK = new JLabel("获得的公钥：");
		lbl_RSA_PK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_PK.setBounds(0, 124, 161, 29);
		panel_RSA.add(lbl_RSA_PK);
		
		JLabel lbl_RSA_SK = new JLabel("获得的私钥：");
		lbl_RSA_SK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_SK.setBounds(0, 160, 161, 29);
		panel_RSA.add(lbl_RSA_SK);
		
		textField_RSA_PK = new JTextField();
		textField_RSA_PK.setEditable(false);
		textField_RSA_PK.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_PK.setColumns(10);
		textField_RSA_PK.setBounds(182, 124, 404, 35);
		panel_RSA.add(textField_RSA_PK);
		
		textField_RSA_SK = new JTextField();
		textField_RSA_SK.setEditable(false);
		textField_RSA_SK.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_SK.setColumns(10);
		textField_RSA_SK.setBounds(182, 160, 404, 35);
		panel_RSA.add(textField_RSA_SK);
		
		JLabel lbl_RSA_N = new JLabel("输入n:");
		lbl_RSA_N.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_N.setBounds(0, 74, 161, 29);
		panel_RSA.add(lbl_RSA_N);
		
		textField_RSA_N = new JTextField();
		textField_RSA_N.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_N.setColumns(10);
		textField_RSA_N.setBounds(182, 74, 404, 35);
		panel_RSA.add(textField_RSA_N);
		
		JButton btn_RSA_Getkey = new JButton("获取密钥");
		btn_RSA_Getkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RSA rsa = new RSA();
				ArrayList<BigInteger> al = new ArrayList<BigInteger>();
				al = rsa.generateKeyPair(al);
				BigInteger e_rsa = (BigInteger) al.get(0);
				BigInteger d = (BigInteger) al.get(1);
				BigInteger n = (BigInteger) al.get(2);
				
				textField_RSA_PK.setText(e_rsa.toString());
				textField_RSA_SK.setText(d.toString());
				textField_RSA_GetN.setText(n.toString());
			}
		});
		btn_RSA_Getkey.setBackground(new Color(204, 204, 255));
		btn_RSA_Getkey.setBounds(677, 156, 127, 37);
		panel_RSA.add(btn_RSA_Getkey);
		
		JLabel lbl_RSA_GetN = new JLabel("得到的n:");
		lbl_RSA_GetN.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_RSA_GetN.setBounds(0, 199, 161, 29);
		panel_RSA.add(lbl_RSA_GetN);
		
		textField_RSA_GetN = new JTextField();
		textField_RSA_GetN.setEditable(false);
		textField_RSA_GetN.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_RSA_GetN.setColumns(10);
		textField_RSA_GetN.setBounds(182, 199, 404, 35);
		panel_RSA.add(textField_RSA_GetN);
		
		JButton btnNewButton_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_RSA_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1.setBounds(676, 195, 129, 37);
		panel_RSA.add(btnNewButton_1_1_1_1);
		
//MD5页面		
		JPanel panel_MD5 = new JPanel();
		panel_MD5.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_MD5, "MD5");
		panel_MD5.setLayout(null);
		
		JLabel lbl_MD5 = new JLabel("MD5");
		lbl_MD5.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_MD5.setBounds(0, 0, 108, 29);
		panel_MD5.add(lbl_MD5);
		
		JLabel lbl_MD5_Str = new JLabel("请输入报文：");
		lbl_MD5_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_MD5_Str.setBounds(0, 61, 189, 29);
		panel_MD5.add(lbl_MD5_Str);
		
		textField_MD5_Message = new JTextField();
		textField_MD5_Message.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_MD5_Message.setBounds(251, 58, 395, 35);
		panel_MD5.add(textField_MD5_Message);
		textField_MD5_Message.setColumns(10);
		
		JLabel lbl_MD5_Digit = new JLabel("生成的报文摘要：");
		lbl_MD5_Digit.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_MD5_Digit.setBounds(0, 118, 211, 29);
		panel_MD5.add(lbl_MD5_Digit);
		
		textField_MD5_Digit = new JTextField();
		textField_MD5_Digit.setEditable(false);
		textField_MD5_Digit.setFont(new Font("Arial", Font.PLAIN, 22));
		textField_MD5_Digit.setColumns(10);
		textField_MD5_Digit.setBounds(251, 114, 395, 35);
		panel_MD5.add(textField_MD5_Digit);
		
		JButton btn_MD5 = new JButton("生成报文摘要");
		btn_MD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MD5 md = new MD5();
				String message = textField_MD5_Message.getText();
				String m = md.generateMD(message);
				textField_MD5_Digit.setText(m);
				textField_MD5_Digit_Up.setText(m.toUpperCase());
			}
		});
		btn_MD5.setBackground(new Color(204, 255, 255));
		btn_MD5.setBounds(532, 260, 202, 37);
		panel_MD5.add(btn_MD5);
		
		JLabel lbl_MD5_Digit_Up = new JLabel("生成报文摘要Upper:");
		lbl_MD5_Digit_Up.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_MD5_Digit_Up.setBounds(0, 175, 238, 29);
		panel_MD5.add(lbl_MD5_Digit_Up);
		
		textField_MD5_Digit_Up = new JTextField();
		textField_MD5_Digit_Up.setEditable(false);
		textField_MD5_Digit_Up.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_MD5_Digit_Up.setColumns(10);
		textField_MD5_Digit_Up.setBounds(251, 171, 395, 35);
		panel_MD5.add(textField_MD5_Digit_Up);
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_MD5_Message.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1.setBounds(696, 57, 129, 37);
		panel_MD5.add(btnNewButton_1_1_1_1_1);

//DH页面
		JPanel panel_DH = new JPanel();
		panel_DH.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_DH, "DH");
		panel_DH.setLayout(null);
		
		JLabel lbl_DH = new JLabel("DH KeyExchange");
		lbl_DH.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_DH.setBounds(213, 0, 344, 50);
		panel_DH.add(lbl_DH);
		
		JLabel lbl_DH_A_PK = new JLabel("甲方公钥：");
		lbl_DH_A_PK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DH_A_PK.setBounds(0, 51, 134, 29);
		panel_DH.add(lbl_DH_A_PK);
		
		textField_DH_A_PK = new JTextField();
		textField_DH_A_PK.setEditable(false);
		textField_DH_A_PK.setBounds(122, 51, 226, 29);
		panel_DH.add(textField_DH_A_PK);
		textField_DH_A_PK.setColumns(10);
		
		JLabel lbl_DH_A_SK = new JLabel("甲方私钥：");
		lbl_DH_A_SK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DH_A_SK.setBounds(0, 90, 134, 29);
		panel_DH.add(lbl_DH_A_SK);
		
		textField_DH_A_SK = new JTextField();
		textField_DH_A_SK.setEditable(false);
		textField_DH_A_SK.setColumns(10);
		textField_DH_A_SK.setBounds(122, 90, 226, 29);
		panel_DH.add(textField_DH_A_SK);
		
		JLabel lbl_DH_B_PK = new JLabel("乙方公钥：");
		lbl_DH_B_PK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DH_B_PK.setBounds(432, 49, 134, 29);
		panel_DH.add(lbl_DH_B_PK);
		
		textField_DH_B_PK = new JTextField();
		textField_DH_B_PK.setEditable(false);
		textField_DH_B_PK.setColumns(10);
		textField_DH_B_PK.setBounds(550, 51, 226, 29);
		panel_DH.add(textField_DH_B_PK);
		
		JLabel lbl_DH_B_SK = new JLabel("乙方私钥：");
		lbl_DH_B_SK.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DH_B_SK.setBounds(432, 90, 134, 29);
		panel_DH.add(lbl_DH_B_SK);
		
		textField_DH_B_SK = new JTextField();
		textField_DH_B_SK.setEditable(false);
		textField_DH_B_SK.setColumns(10);
		textField_DH_B_SK.setBounds(550, 90, 226, 29);
		panel_DH.add(textField_DH_B_SK);
		
		JLabel lbl_DH_A_Local = new JLabel("甲方本地密钥:");
		lbl_DH_A_Local.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_A_Local.setBounds(0, 128, 172, 29);
		panel_DH.add(lbl_DH_A_Local);
		
		textField_DH_A_Local = new JTextField();
		textField_DH_A_Local.setEditable(false);
		textField_DH_A_Local.setColumns(10);
		textField_DH_A_Local.setBounds(149, 128, 226, 29);
		panel_DH.add(textField_DH_A_Local);
		
		JLabel lbl_DH_B_Local = new JLabel("乙方本地密钥:");
		lbl_DH_B_Local.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_B_Local.setBounds(427, 128, 167, 29);
		panel_DH.add(lbl_DH_B_Local);
		
		textField_DH_B_Local = new JTextField();
		textField_DH_B_Local.setEditable(false);
		textField_DH_B_Local.setColumns(10);
		textField_DH_B_Local.setBounds(577, 128, 226, 29);
		panel_DH.add(textField_DH_B_Local);
		
		JLabel lbl_DH_A_Send_M = new JLabel("甲发送给乙的消息：");
		lbl_DH_A_Send_M.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_A_Send_M.setBounds(0, 198, 239, 29);
		panel_DH.add(lbl_DH_A_Send_M);
		
		textField_DH_A_Send = new JTextField();
		textField_DH_A_Send.setColumns(10);
		textField_DH_A_Send.setBounds(213, 198, 226, 29);
		panel_DH.add(textField_DH_A_Send);
		
		JLabel lbl_DH_A_Local_EN = new JLabel("甲本地密钥加密：");
		lbl_DH_A_Local_EN.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_A_Local_EN.setBounds(0, 231, 184, 29);
		panel_DH.add(lbl_DH_A_Local_EN);
		
		textField_DH_A_local_EN = new JTextField();
		textField_DH_A_local_EN.setEditable(false);
		textField_DH_A_local_EN.setColumns(10);
		textField_DH_A_local_EN.setBounds(180, 231, 226, 29);
		panel_DH.add(textField_DH_A_local_EN);
		
		JLabel lbl_DH_B_Local_DE = new JLabel("乙本地密钥解密：");
		lbl_DH_B_Local_DE.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_B_Local_DE.setBounds(427, 231, 190, 29);
		panel_DH.add(lbl_DH_B_Local_DE);
		
		textField_DH_B_DE = new JTextField();
		textField_DH_B_DE.setEditable(false);
		textField_DH_B_DE.setColumns(10);
		textField_DH_B_DE.setBounds(599, 231, 226, 29);
		panel_DH.add(textField_DH_B_DE);
		
		JLabel lbl_DH_B_Send_M = new JLabel("乙发送给甲的消息：");
		lbl_DH_B_Send_M.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_B_Send_M.setBounds(0, 302, 239, 29);
		panel_DH.add(lbl_DH_B_Send_M);
		
		JLabel lbl_DH_B_Local_EN = new JLabel("乙本地密钥加密：");
		lbl_DH_B_Local_EN.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_B_Local_EN.setBounds(0, 335, 184, 29);
		panel_DH.add(lbl_DH_B_Local_EN);
		
		textField_DH_B_Send = new JTextField();
		textField_DH_B_Send.setColumns(10);
		textField_DH_B_Send.setBounds(213, 302, 226, 29);
		panel_DH.add(textField_DH_B_Send);
		
		textField_DH_B_EN = new JTextField();
		textField_DH_B_EN.setEditable(false);
		textField_DH_B_EN.setColumns(10);
		textField_DH_B_EN.setBounds(180, 335, 226, 29);
		panel_DH.add(textField_DH_B_EN);
		
		JLabel lbl_DH_A_Local_DE = new JLabel("甲本地密钥解密：");
		lbl_DH_A_Local_DE.setFont(new Font("宋体", Font.PLAIN, 23));
		lbl_DH_A_Local_DE.setBounds(427, 335, 190, 29);
		panel_DH.add(lbl_DH_A_Local_DE);
		
		textField_DH_A_DE = new JTextField();
		textField_DH_A_DE.setEditable(false);
		textField_DH_A_DE.setColumns(10);
		textField_DH_A_DE.setBounds(599, 335, 226, 29);
		panel_DH.add(textField_DH_A_DE);
		
		JButton btn_getKey = new JButton("获取密钥");
		btn_getKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//甲方公钥
		        byte[] publicKey1;
		        //甲方私钥
		        byte[] privateKey1;
		        //甲方本地密钥
		        byte[] key1;
		        //乙方公钥
		        byte[] publicKey2;
		        //乙方私钥
		        byte[] privateKey2;
		        //乙方本地密钥
		        byte[] key2;
		        
		        DH dh = new DH();
		        //初始化密钥
		        //生成甲方密钥对
		        Map<String, Object> keyMap1;
				try {
					keyMap1 = dh.initKey();
			        publicKey1 = dh.getPublicKey(keyMap1);
			        privateKey1 = dh.getPrivateKey(keyMap1);
			        textField_DH_A_PK.setText(Base64Utils.encode(publicKey1));
			        textField_DH_A_SK.setText(Base64Utils.encode(privateKey1));
			        
			        //由甲方公钥产生乙方本地密钥对
			        Map<String, Object> keyMap2 = dh.initKey(publicKey1);
			        publicKey2 = dh.getPublicKey(keyMap2);
			        privateKey2 = dh.getPrivateKey(keyMap2);
			        textField_DH_B_PK.setText(Base64Utils.encode(publicKey2));
			        textField_DH_B_SK.setText(Base64Utils.encode(privateKey2));

			        key1 = dh.getSecretKey(publicKey2, privateKey1);
			        textField_DH_A_Local.setText(Base64Utils.encode(key1));
			        
			        key2 = dh.getSecretKey(publicKey1, privateKey2);
			        textField_DH_B_Local.setText(Base64Utils.encode(key2));
			        
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_getKey.setBackground(new Color(204, 255, 255));
		btn_getKey.setBounds(650, 7, 153, 37);
		panel_DH.add(btn_getKey);
		
		JButton btn_DH_A_En = new JButton("甲方加密");
		btn_DH_A_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DH dh = new DH();
		        String input1 = textField_DH_A_Send.getText();
		        //使用甲方本地密钥对数据加密
		        byte[] encode1;
				try {
					encode1 = dh.encrypt(input1.getBytes(),Base64Utils.decode(textField_DH_A_Local.getText()));
					textField_DH_A_local_EN.setText(Base64Utils.encode(encode1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btn_DH_A_En.setBackground(new Color(153, 204, 102));
		btn_DH_A_En.setBounds(519, 190, 153, 37);
		panel_DH.add(btn_DH_A_En);
		
		JButton btn_DH_B_De = new JButton("乙方解密");
		btn_DH_B_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DH dh = new DH();
				
		        //使用乙方本地密钥对数据进行解密
		        byte[] decode1;
				try {
					decode1 = dh.decrypt(Base64Utils.decode(textField_DH_A_local_EN.getText()), Base64Utils.decode(textField_DH_B_Local.getText()));
					String output1 = new String(decode1);
					textField_DH_B_DE.setText(output1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		});
		btn_DH_B_De.setBackground(new Color(255, 204, 153));
		btn_DH_B_De.setBounds(682, 190, 153, 37);
		panel_DH.add(btn_DH_B_De);
		
		JButton btn_DH_B_En = new JButton("甲方解密");
		btn_DH_B_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DH dh = new DH();
		        //使用甲方本地密钥对数据进行解密
		        byte[] decode2;
				try {
					decode2 = dh.decrypt(Base64Utils.decode(textField_DH_B_EN.getText()), Base64Utils.decode(textField_DH_A_Local.getText()));
					String output2 = new String(decode2);
					textField_DH_A_DE.setText(output2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_DH_B_En.setBackground(new Color(255, 204, 153));
		btn_DH_B_En.setBounds(682, 294, 153, 37);
		panel_DH.add(btn_DH_B_En);
		
		JButton btn_DH_A_De = new JButton("乙方加密");
		btn_DH_A_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DH dh = new DH();
		        String input2 = textField_DH_B_Send.getText();
		        byte[] encode2;
				try {
					encode2 = dh.encrypt(input2.getBytes(), Base64Utils.decode(textField_DH_B_Local.getText()));
					textField_DH_B_EN.setText(Base64Utils.encode(encode2));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_DH_A_De.setBackground(new Color(153, 204, 102));
		btn_DH_A_De.setBounds(519, 294, 153, 37);
		panel_DH.add(btn_DH_A_De);
		
		JButton btnNewButton_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u7532\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_DH_A_Send.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1.setBounds(5, 261, 129, 37);
		panel_DH.add(btnNewButton_1_1_1_1_1_1);
		
		JButton btnNewButton_1_1_1_1_1_2 = new JButton("\u6253\u5F00\u4E59\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_DH_B_Send.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_2.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_2.setBounds(149, 261, 129, 37);
		panel_DH.add(btnNewButton_1_1_1_1_1_2);

//Vigenere页面
		JPanel panel_Vigenere = new JPanel();
		panel_Vigenere.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Vigenere, "Vigenere");
		panel_Vigenere.setLayout(null);
		
		JLabel lbl_Vigenere = new JLabel("Vigenere");
		lbl_Vigenere.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_Vigenere.setBounds(679, 0, 167, 43);
		panel_Vigenere.add(lbl_Vigenere);
		
		JLabel lbl_Vigenere_Str = new JLabel("输入英文字符串：");
		lbl_Vigenere_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Vigenere_Str.setBounds(0, 39, 253, 29);
		panel_Vigenere.add(lbl_Vigenere_Str);
		
		textField_Vigenere_Str = new JTextField();
		textField_Vigenere_Str.setBounds(54, 73, 525, 35);
		panel_Vigenere.add(textField_Vigenere_Str);
		textField_Vigenere_Str.setColumns(10);
		
		JLabel lbl_Vigenere_Key = new JLabel("输入密钥：");
		lbl_Vigenere_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Vigenere_Key.setBounds(0, 129, 253, 29);
		panel_Vigenere.add(lbl_Vigenere_Key);
		
		textField_Vigenere_Key = new JTextField();
		textField_Vigenere_Key.setColumns(10);
		textField_Vigenere_Key.setBounds(54, 163, 525, 35);
		panel_Vigenere.add(textField_Vigenere_Key);
		
		JButton btn_Vigenere_En = new JButton("加密");
		btn_Vigenere_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vigenere v = new vigenere();
				String key = textField_Vigenere_Key.getText();
				String plaintext = textField_Vigenere_Str.getText();
				String str = "";
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char plaintext0[] = new char[plaintext.length()];
				plaintext0 = plaintext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < plaintext0.length;i++) {
					if(plaintext0[i] - 97 < 0 || plaintext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = v.Encryptioncore(textField_Vigenere_Key.getText(), textField_Vigenere_Str.getText());
				}
				
				textArea_Vigenere.setText(str);
			}
		});
		btn_Vigenere_En.setBackground(new Color(153, 204, 153));
		btn_Vigenere_En.setBounds(627, 72, 153, 37);
		panel_Vigenere.add(btn_Vigenere_En);
		
		JButton btn_Vigenere_De = new JButton("解密");
		btn_Vigenere_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vigenere v = new vigenere();
				
				String key = textField_Vigenere_Key.getText();
				String ciphertext = textField_Vigenere_Str.getText();
				String str = "";
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char ciphertext0[] = new char[ciphertext.length()];
				ciphertext0 = ciphertext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < ciphertext0.length;i++) {
					if(ciphertext0[i] - 97 < 0 || ciphertext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = v.Decryptioncore(textField_Vigenere_Key.getText(), textField_Vigenere_Str.getText());
				}

				textArea_Vigenere.setText(str);
			}
		});
		btn_Vigenere_De.setBackground(new Color(204, 255, 255));
		btn_Vigenere_De.setBounds(627, 162, 153, 37);
		panel_Vigenere.add(btn_Vigenere_De);
		
		JLabel lbl_Vigenere_Result = new JLabel("结果：");
		lbl_Vigenere_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Vigenere_Result.setBounds(0, 219, 108, 29);
		panel_Vigenere.add(lbl_Vigenere_Result);
		
		JScrollPane scrollPane_Vigenere = new JScrollPane();
		scrollPane_Vigenere.setBounds(10, 256, 825, 108);
		panel_Vigenere.add(scrollPane_Vigenere);
		
		textArea_Vigenere = new JTextArea();
		textArea_Vigenere.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_Vigenere.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Vigenere.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Vigenere.setViewportView(textArea_Vigenere);
		
		JButton btnNewButton_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Vigenere_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1.setBounds(100, 215, 129, 37);
		panel_Vigenere.add(btnNewButton_1_1_1_1_1_1_1);

//Autokey Ciphertext界面
		JPanel panel_Autokey_C = new JPanel();
		panel_Autokey_C.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Autokey_C, "Autokey Ciphertext");
		panel_Autokey_C.setLayout(null);
		
		JLabel lbl_Autokey_C = new JLabel("Autokey Ciphertext");
		lbl_Autokey_C.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_Autokey_C.setBounds(455, 0, 370, 52);
		panel_Autokey_C.add(lbl_Autokey_C);
		
		JLabel lbl_Autokey_C_Str = new JLabel("\u8F93\u5165\u82F1\u6587\u5B57\u7B26\u4E32\uFF1A");
		lbl_Autokey_C_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_C_Str.setBounds(0, 39, 253, 29);
		panel_Autokey_C.add(lbl_Autokey_C_Str);
		
		textField_Autokey_C_Str = new JTextField();
		textField_Autokey_C_Str.setColumns(10);
		textField_Autokey_C_Str.setBounds(54, 73, 525, 35);
		panel_Autokey_C.add(textField_Autokey_C_Str);
		
		JLabel lbl_Autokey_C_Key = new JLabel("\u8F93\u5165\u5BC6\u94A5\uFF1A");
		lbl_Autokey_C_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_C_Key.setBounds(0, 129, 253, 29);
		panel_Autokey_C.add(lbl_Autokey_C_Key);
		
		textField_Autokey_C_Key = new JTextField();
		textField_Autokey_C_Key.setColumns(10);
		textField_Autokey_C_Key.setBounds(54, 163, 525, 35);
		panel_Autokey_C.add(textField_Autokey_C_Key);
		
		JButton btn_Autokey_C_En = new JButton("\u52A0\u5BC6");
		btn_Autokey_C_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AutokeyCipher ac = new AutokeyCipher();				
				String key = textField_Autokey_C_Key.getText();
				String plaintext = textField_Autokey_C_Str.getText();
				String str = "";
				
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char plaintext0[] = new char[plaintext.length()];
				plaintext0 = plaintext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < plaintext0.length;i++) {
					if(plaintext0[i] - 97 < 0 || plaintext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = ac.Encryptioncore(textField_Autokey_C_Key.getText(), textField_Autokey_C_Str.getText());
				}
				
				textArea_Autokey_C.setText(str);
			}
		});
		btn_Autokey_C_En.setBackground(new Color(153, 204, 153));
		btn_Autokey_C_En.setBounds(627, 72, 153, 37);
		panel_Autokey_C.add(btn_Autokey_C_En);
		
		JButton btn_Autokey_C_De = new JButton("\u89E3\u5BC6");
		btn_Autokey_C_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AutokeyCipher ac = new AutokeyCipher();
				
				String key = textField_Autokey_C_Key.getText();
				String ciphertext = textField_Autokey_C_Str.getText();
				String str = "";
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char ciphertext0[] = new char[ciphertext.length()];
				ciphertext0 = ciphertext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < ciphertext0.length;i++) {
					if(ciphertext0[i] - 97 < 0 || ciphertext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = ac.Decryptioncore(textField_Autokey_C_Key.getText(), textField_Autokey_C_Str.getText());
				}
				
				textArea_Autokey_C.setText(str);
			}
		});
		btn_Autokey_C_De.setBackground(new Color(204, 255, 255));
		btn_Autokey_C_De.setBounds(627, 162, 153, 37);
		panel_Autokey_C.add(btn_Autokey_C_De);
		
		JLabel lbl_Autokey_C_Result = new JLabel("\u7ED3\u679C\uFF1A");
		lbl_Autokey_C_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_C_Result.setBounds(0, 219, 108, 29);
		panel_Autokey_C.add(lbl_Autokey_C_Result);
		
		JScrollPane scrollPane_Autokey_C = new JScrollPane();
		scrollPane_Autokey_C.setBounds(10, 256, 825, 108);
		panel_Autokey_C.add(scrollPane_Autokey_C);
		
		textArea_Autokey_C = new JTextArea();
		textArea_Autokey_C.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_Autokey_C.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Autokey_C.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Autokey_C.setViewportView(textArea_Autokey_C);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Autokey_C_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1.setBounds(64, 215, 129, 37);
		panel_Autokey_C.add(btnNewButton_1_1_1_1_1_1_1_1);

//Autokey Plaintext界面
		JPanel panel_Autokey_P = new JPanel();
		panel_Autokey_P.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Autokey_P, "Autokey Plaintext");
		panel_Autokey_P.setLayout(null);
		
		JLabel lbl_Autokey_P = new JLabel("Autokey Plaintext");
		lbl_Autokey_P.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_Autokey_P.setBounds(455, 0, 370, 52);
		panel_Autokey_P.add(lbl_Autokey_P);
		
		JLabel lbl_Autokey_P_Str = new JLabel("\u8F93\u5165\u82F1\u6587\u5B57\u7B26\u4E32\uFF1A");
		lbl_Autokey_P_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_P_Str.setBounds(0, 39, 253, 29);
		panel_Autokey_P.add(lbl_Autokey_P_Str);
		
		textField_Autokey_P_Str = new JTextField();
		textField_Autokey_P_Str.setColumns(10);
		textField_Autokey_P_Str.setBounds(54, 73, 525, 35);
		panel_Autokey_P.add(textField_Autokey_P_Str);
		
		JLabel lbl_Autokey_P_Key = new JLabel("\u8F93\u5165\u5BC6\u94A5\uFF1A");
		lbl_Autokey_P_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_P_Key.setBounds(0, 129, 253, 29);
		panel_Autokey_P.add(lbl_Autokey_P_Key);
		
		textField_Autokey_P_Key = new JTextField();
		textField_Autokey_P_Key.setColumns(10);
		textField_Autokey_P_Key.setBounds(54, 163, 525, 35);
		panel_Autokey_P.add(textField_Autokey_P_Key);
		
		JButton btn_Autokey_P_En = new JButton("\u52A0\u5BC6");
		btn_Autokey_P_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AutokeyPlaintext ap = new AutokeyPlaintext();				
				String key = textField_Autokey_P_Key.getText();
				String plaintext = textField_Autokey_P_Str.getText();
				String str = "";
				
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char plaintext0[] = new char[plaintext.length()];
				plaintext0 = plaintext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < plaintext0.length;i++) {
					if(plaintext0[i] - 97 < 0 || plaintext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = ap.Encryptioncore(textField_Autokey_P_Key.getText(), textField_Autokey_P_Str.getText());
				}
				
				textArea_Autokey_P.setText(str);
			}
		});
		btn_Autokey_P_En.setBackground(new Color(153, 204, 153));
		btn_Autokey_P_En.setBounds(627, 72, 153, 37);
		panel_Autokey_P.add(btn_Autokey_P_En);
		
		JButton btn_Autokey_P_De = new JButton("\u89E3\u5BC6");
		btn_Autokey_P_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AutokeyPlaintext ap = new AutokeyPlaintext();
				
				String key = textField_Autokey_P_Key.getText();
				String ciphertext = textField_Autokey_P_Str.getText();
				String str = "";
		//判断输入是否合法
				char key0[] = new char[key.length()];
				key0 = key.toCharArray();
				int count1 = 0;
				for(int i = 0;i < key.length();i++) {
					if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
						str = "Invalid!";
						count1++;
						break;
					}
				}
				
				char ciphertext0[] = new char[ciphertext.length()];
				ciphertext0 = ciphertext.toCharArray();
				int count2 = 0;
				for(int i = 0;i < ciphertext0.length;i++) {
					if(ciphertext0[i] - 97 < 0 || ciphertext0[i] - 97 > 26) {
						str = "Invalid!";
						count2++;
						break;
					}
				}
				
				if(count1 == 0 && count2 == 0) {
					str = ap.Decryptioncore(textField_Autokey_P_Key.getText(), textField_Autokey_P_Str.getText());
				}
				
				textArea_Autokey_P.setText(str);
			}
		});
		btn_Autokey_P_De.setBackground(new Color(204, 255, 255));
		btn_Autokey_P_De.setBounds(627, 162, 153, 37);
		panel_Autokey_P.add(btn_Autokey_P_De);
		
		JLabel lbl_Autokey_P_Result = new JLabel("\u7ED3\u679C\uFF1A");
		lbl_Autokey_P_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Autokey_P_Result.setBounds(0, 219, 108, 29);
		panel_Autokey_P.add(lbl_Autokey_P_Result);
		
		JScrollPane scrollPane_Autokey_P = new JScrollPane();
		scrollPane_Autokey_P.setBounds(10, 256, 825, 108);
		panel_Autokey_P.add(scrollPane_Autokey_P);
		
		textArea_Autokey_P = new JTextArea();		
		textArea_Autokey_P.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_Autokey_P.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Autokey_P.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Autokey_P.setViewportView(textArea_Autokey_P);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Autokey_P_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1.setBounds(64, 215, 129, 37);
		panel_Autokey_P.add(btnNewButton_1_1_1_1_1_1_1_1_1);
		
//playfair页面
		JPanel panel_Playfair = new JPanel();
		panel_Playfair.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Playfair, "Playfair Cipher");
		panel_Playfair.setLayout(null);
		
		JLabel lbl_Playfair = new JLabel("Playfair");
		lbl_Playfair.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_Playfair.setBounds(662, 0, 184, 50);
		panel_Playfair.add(lbl_Playfair);
		
		JLabel lbl_Playfair_Str = new JLabel("输入字符串：");
		lbl_Playfair_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Playfair_Str.setBounds(10, 31, 161, 29);
		panel_Playfair.add(lbl_Playfair_Str);
		
		textField_Playfair_Str = new JTextField();
		textField_Playfair_Str.setBounds(58, 67, 543, 35);
		panel_Playfair.add(textField_Playfair_Str);
		textField_Playfair_Str.setColumns(10);
		
		JLabel lbl_Playfair_Key = new JLabel("输入密文：");
		lbl_Playfair_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Playfair_Key.setBounds(10, 123, 161, 29);
		panel_Playfair.add(lbl_Playfair_Key);
		
		textField_Playfair_Key = new JTextField();
		textField_Playfair_Key.setColumns(10);
		textField_Playfair_Key.setBounds(58, 159, 543, 35);
		panel_Playfair.add(textField_Playfair_Key);
		
		JLabel lbl_Playfair_Result = new JLabel("结果：");
		lbl_Playfair_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Playfair_Result.setBounds(10, 215, 108, 29);
		panel_Playfair.add(lbl_Playfair_Result);
		
		JScrollPane scrollPane_Playfair = new JScrollPane();
		scrollPane_Playfair.setBounds(10, 247, 815, 128);
		panel_Playfair.add(scrollPane_Playfair);
		
		textArea_Playfair = new JTextArea();
		textArea_Playfair.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_Playfair.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Playfair.setWrapStyleWord(true);		// 激活断行不断字功能
		scrollPane_Playfair.setViewportView(textArea_Playfair);
		
		JButton btn_Playfair_En = new JButton("加密");
		btn_Playfair_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestPlayFair playfair = new TestPlayFair();
				String plain = textField_Playfair_Str.getText();
				String key = textField_Playfair_Key.getText();
				key = playfair.format(key);
				plain = playfair.format(plain);
				String[] str = playfair.encode(plain,key);
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < str.length; i++){
					if(str[i] != null)
					sb.append(str[i]);
				}
				textArea_Playfair.setText(sb.toString());
			}
		});
		btn_Playfair_En.setBackground(new Color(153, 204, 153));
		btn_Playfair_En.setBounds(662, 66, 153, 37);
		panel_Playfair.add(btn_Playfair_En);
		
		JButton btn_Playfair_De = new JButton("解密");
		btn_Playfair_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestPlayFair play = new TestPlayFair();
				String cipher = textField_Playfair_Str.getText();
				String key = textField_Playfair_Key.getText();
				key = play.format(key);
				
				String[] str = play.decode(cipher,key);
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < str.length; i++){
					if(str[i] != null)
					sb.append(str[i]);
				}
				textArea_Playfair.setText(sb.toString());
			}
		});
		btn_Playfair_De.setBackground(new Color(204, 255, 255));
		btn_Playfair_De.setBounds(662, 158, 153, 37);
		panel_Playfair.add(btn_Playfair_De);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Playfair_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1.setBounds(80, 207, 129, 37);
		panel_Playfair.add(btnNewButton_1_1_1_1_1_1_1_1_1_1);
		
		
//Permutation页面
		JPanel panel_Permutation = new JPanel();
		panel_Permutation.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Permutation, "Permutation");
		panel_Permutation.setLayout(null);
		
		JLabel lbl_Permutation = new JLabel("Permutation Cipher");
		lbl_Permutation.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_Permutation.setBounds(226, 0, 374, 50);
		panel_Permutation.add(lbl_Permutation);
		
		JLabel lbl_Permutation_Str = new JLabel("输入明文字符串：");
		lbl_Permutation_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Permutation_Str.setBounds(21, 46, 210, 29);
		panel_Permutation.add(lbl_Permutation_Str);
		
		textField_Permutation_Str = new JTextField();
		textField_Permutation_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_Permutation_Str.setBounds(21, 82, 615, 35);
		panel_Permutation.add(textField_Permutation_Str);
		textField_Permutation_Str.setColumns(10);
		
		textField_Permutation_Key = new JTextField();
		textField_Permutation_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_Permutation_Key.setColumns(10);
		textField_Permutation_Key.setBounds(21, 169, 615, 35);
		panel_Permutation.add(textField_Permutation_Key);
		
		JLabel lbl_Permutation_Key = new JLabel("输入密钥：");
		lbl_Permutation_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Permutation_Key.setBounds(21, 133, 187, 29);
		panel_Permutation.add(lbl_Permutation_Key);
		
		JLabel lbl_Permutation_Result = new JLabel("结果：");
		lbl_Permutation_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Permutation_Result.setBounds(21, 216, 108, 29);
		panel_Permutation.add(lbl_Permutation_Result);
		
		JScrollPane scrollPane_Permutation = new JScrollPane();
		scrollPane_Permutation.setBounds(21, 255, 804, 120);
		panel_Permutation.add(scrollPane_Permutation);
		
		textArea_Permutation = new JTextArea();
		textArea_Permutation.setLineWrap(true);        	// 激活自动换行功能 
		textArea_Permutation.setWrapStyleWord(true);		// 激活断行不断字功能
		textArea_Permutation.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane_Permutation.setViewportView(textArea_Permutation);
		
		JButton btn_Permutation_EN = new JButton("加密");
		btn_Permutation_EN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Permutation p = new Permutation();
				String result = "";
				String plain = textField_Permutation_Str.getText();
				String key = textField_Permutation_Key.getText();
				result = p.code(1, plain, key);
				textArea_Permutation.setText(result);
				
			}
		});
		btn_Permutation_EN.setBackground(new Color(153, 204, 153));
		btn_Permutation_EN.setBounds(672, 81, 153, 37);
		panel_Permutation.add(btn_Permutation_EN);
		
		JButton btn_Permutation_DE = new JButton("解密");
		btn_Permutation_DE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Permutation p = new Permutation();
				String result = "";
				String cipher = textField_Permutation_Str.getText();
				String key = textField_Permutation_Key.getText();
				result = p.code(2, cipher, key);
				textArea_Permutation.setText(result);
				
			}
		});
		btn_Permutation_DE.setBackground(new Color(204, 255, 255));
		btn_Permutation_DE.setBounds(672, 168, 153, 37);
		panel_Permutation.add(btn_Permutation_DE);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Permutation_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1.setBounds(102, 212, 129, 37);
		panel_Permutation.add(btnNewButton_1_1_1_1_1_1_1_1_1_1_1);
	
//Column Permtation页面
		JPanel panel_ColumnPermutation = new JPanel();
		panel_ColumnPermutation.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_ColumnPermutation, "Column Permutation");
		panel_ColumnPermutation.setLayout(null);
		
		JLabel lbl_ColumnPermutation = new JLabel("Column Permutation Cipher");
		lbl_ColumnPermutation.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_ColumnPermutation.setBounds(133, 0, 564, 50);
		panel_ColumnPermutation.add(lbl_ColumnPermutation);
		
		JLabel lbl_ColumnPermutation_Str = new JLabel("\u8F93\u5165\u660E\u6587\u5B57\u7B26\u4E32\uFF1A");
		lbl_ColumnPermutation_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_ColumnPermutation_Str.setBounds(21, 46, 210, 29);
		panel_ColumnPermutation.add(lbl_ColumnPermutation_Str);
		
		textField_ColumnPermutation_Str = new JTextField();
		textField_ColumnPermutation_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_ColumnPermutation_Str.setColumns(10);
		textField_ColumnPermutation_Str.setBounds(21, 82, 615, 35);
		panel_ColumnPermutation.add(textField_ColumnPermutation_Str);
		
		textField_ColumnPermutation_Key = new JTextField();
		textField_ColumnPermutation_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_ColumnPermutation_Key.setColumns(10);
		textField_ColumnPermutation_Key.setBounds(21, 169, 615, 35);
		panel_ColumnPermutation.add(textField_ColumnPermutation_Key);
		
		JLabel lbl_ColumnPermutation_Key = new JLabel("\u8F93\u5165\u5BC6\u94A5\uFF1A");
		lbl_ColumnPermutation_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_ColumnPermutation_Key.setBounds(21, 133, 187, 29);
		panel_ColumnPermutation.add(lbl_ColumnPermutation_Key);
		
		JLabel lbl_ColumnPermutation_Result = new JLabel("\u7ED3\u679C\uFF1A");
		lbl_ColumnPermutation_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_ColumnPermutation_Result.setBounds(21, 216, 108, 29);
		panel_ColumnPermutation.add(lbl_ColumnPermutation_Result);
		
		JScrollPane scrollPane_ColumnPermutation = new JScrollPane();
		scrollPane_ColumnPermutation.setBounds(21, 255, 804, 120);
		panel_ColumnPermutation.add(scrollPane_ColumnPermutation);
		
		textArea_ColumnPermutation = new JTextArea();
		textArea_ColumnPermutation.setLineWrap(true);        	// 激活自动换行功能 
		textArea_ColumnPermutation.setWrapStyleWord(true);		// 激活断行不断字功能
		textArea_ColumnPermutation.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane_ColumnPermutation.setViewportView(textArea_ColumnPermutation);
		
		JButton btn_ColumnPermutation_EN = new JButton("\u52A0\u5BC6");
		btn_ColumnPermutation_EN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				column_permutation cp = new column_permutation();
				String plain = textField_ColumnPermutation_Str.getText();
				String key = textField_ColumnPermutation_Key.getText();
				String str = cp.Encryptioncore(key, plain);
				textArea_ColumnPermutation.setText(str);
			}
		});
		btn_ColumnPermutation_EN.setBackground(new Color(153, 204, 153));
		btn_ColumnPermutation_EN.setBounds(672, 81, 153, 37);
		panel_ColumnPermutation.add(btn_ColumnPermutation_EN);
		
		JButton btn_ColumnPermutation_DE = new JButton("\u89E3\u5BC6");
		btn_ColumnPermutation_DE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				column_permutation cp = new column_permutation();
				String cipher = textField_ColumnPermutation_Str.getText();
				String key = textField_ColumnPermutation_Key.getText();
				String str = cp.Decryptioncore(key, cipher);
				textArea_ColumnPermutation.setText(str);
			}
		});
		btn_ColumnPermutation_DE.setBackground(new Color(204, 255, 255));
		btn_ColumnPermutation_DE.setBounds(672, 168, 153, 37);
		panel_ColumnPermutation.add(btn_ColumnPermutation_DE);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_ColumnPermutation_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1.setBounds(102, 212, 129, 37);
		panel_ColumnPermutation.add(btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1);

//Double Transposition页面
		JPanel panel_DoubleTransporsition = new JPanel();
		panel_DoubleTransporsition.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_DoubleTransporsition, "Double Transposition");
		panel_DoubleTransporsition.setLayout(null);
		
		JLabel lbl_DoubleTransposition = new JLabel("Double Transposition Cipher");
		lbl_DoubleTransposition.setFont(new Font("Bradley Hand ITC", Font.BOLD, 40));
		lbl_DoubleTransposition.setBounds(133, 0, 564, 50);
		panel_DoubleTransporsition.add(lbl_DoubleTransposition);
		
		JLabel lbl_DoubleTransposition_Str = new JLabel("\u8F93\u5165\u660E\u6587\u5B57\u7B26\u4E32\uFF1A");
		lbl_DoubleTransposition_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DoubleTransposition_Str.setBounds(21, 46, 210, 29);
		panel_DoubleTransporsition.add(lbl_DoubleTransposition_Str);
		
		textField_DoubleTransposition_Str = new JTextField();
		textField_DoubleTransposition_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_DoubleTransposition_Str.setColumns(10);
		textField_DoubleTransposition_Str.setBounds(21, 82, 615, 35);
		panel_DoubleTransporsition.add(textField_DoubleTransposition_Str);
		
		textField_DoubleTransposition_Key1 = new JTextField();
		textField_DoubleTransposition_Key1.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_DoubleTransposition_Key1.setColumns(10);
		textField_DoubleTransposition_Key1.setBounds(21, 169, 305, 35);
		panel_DoubleTransporsition.add(textField_DoubleTransposition_Key1);
		
		JLabel lbl_DoubleTransposition_Key1 = new JLabel("输入第一次密钥：");
		lbl_DoubleTransposition_Key1.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DoubleTransposition_Key1.setBounds(21, 133, 231, 29);
		panel_DoubleTransporsition.add(lbl_DoubleTransposition_Key1);
		
		JLabel lbl_DoubleTransposition_Result = new JLabel("\u7ED3\u679C\uFF1A");
		lbl_DoubleTransposition_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DoubleTransposition_Result.setBounds(21, 216, 108, 29);
		panel_DoubleTransporsition.add(lbl_DoubleTransposition_Result);
		
		JScrollPane scrollPane_DoubleTransposition = new JScrollPane();
		scrollPane_DoubleTransposition.setBounds(21, 255, 804, 120);
		panel_DoubleTransporsition.add(scrollPane_DoubleTransposition);
		
		textArea_DoubleTransposition = new JTextArea();
		textArea_DoubleTransposition.setLineWrap(true);        	// 激活自动换行功能 
		textArea_DoubleTransposition.setWrapStyleWord(true);		// 激活断行不断字功能
		textArea_DoubleTransposition.setFont(new Font("宋体", Font.PLAIN, 20));
		textArea_DoubleTransposition.setText("");
		scrollPane_DoubleTransposition.setViewportView(textArea_DoubleTransposition);
		
		JButton btn_DoubleTransposition_EN = new JButton("\u52A0\u5BC6");
		btn_DoubleTransposition_EN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoubleTransposionCipher dt = new DoubleTransposionCipher();
				String plain = textField_DoubleTransposition_Str.getText();
				String key1 = textField_DoubleTransposition_Key1.getText();
				String key2 = textField_DoubleTransposition_Key2.getText();
				
				String str = dt.Encryptioncore(key2,dt.Encryptioncore(key1, plain));
				textArea_DoubleTransposition.setText(str);
			}
		});
		btn_DoubleTransposition_EN.setBackground(new Color(153, 204, 153));
		btn_DoubleTransposition_EN.setBounds(672, 81, 153, 37);
		panel_DoubleTransporsition.add(btn_DoubleTransposition_EN);
		
		JButton btn_DoubleTransposition_DE = new JButton("\u89E3\u5BC6");
		btn_DoubleTransposition_DE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoubleTransposionCipher dt = new DoubleTransposionCipher();
				String cipher = textField_DoubleTransposition_Str.getText();
				String key1 = textField_DoubleTransposition_Key1.getText();
				String key2 = textField_DoubleTransposition_Key2.getText();
				
				String str = dt.Decryptioncore(key1,dt.Decryptioncore(key2, cipher));
				textArea_DoubleTransposition.setText(str);
			}
		});
		btn_DoubleTransposition_DE.setBackground(new Color(204, 255, 255));
		btn_DoubleTransposition_DE.setBounds(672, 168, 153, 37);
		panel_DoubleTransporsition.add(btn_DoubleTransposition_DE);
		
		JLabel lbl_DoubleTransposition_Key_2 = new JLabel("输入第二次密钥：");
		lbl_DoubleTransposition_Key_2.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DoubleTransposition_Key_2.setBounds(336, 133, 236, 29);
		panel_DoubleTransporsition.add(lbl_DoubleTransposition_Key_2);
		
		textField_DoubleTransposition_Key2 = new JTextField();
		textField_DoubleTransposition_Key2.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_DoubleTransposition_Key2.setColumns(10);
		textField_DoubleTransposition_Key2.setBounds(336, 169, 305, 35);
		panel_DoubleTransporsition.add(textField_DoubleTransposition_Key2);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_DoubleTransposition_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1.setBounds(107, 212, 129, 37);
		panel_DoubleTransporsition.add(btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1);
		

//Affine界面
		JPanel panel_Affine = new JPanel();
		panel_Affine.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Affine, "Affine");
		panel_Affine.setLayout(null);
		
		JLabel lbl_Affine = new JLabel("Affine");
		lbl_Affine.setFont(new Font("Bradley Hand ITC", Font.BOLD, 50));
		lbl_Affine.setBounds(391, 16, 274, 57);
		panel_Affine.add(lbl_Affine);
		
		JLabel lbl_Affine_Str = new JLabel("输入英文字符串：");
		lbl_Affine_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Affine_Str.setBounds(10, 61, 285, 29);
		panel_Affine.add(lbl_Affine_Str);
		
		textField_Affine_Str = new JTextField();
		textField_Affine_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_Affine_Str.setBounds(10, 94, 631, 35);
		panel_Affine.add(textField_Affine_Str);
		textField_Affine_Str.setColumns(10);
		
		JLabel lbl_Affine_Key1 = new JLabel("输入密钥1：");
		lbl_Affine_Key1.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Affine_Key1.setBounds(10, 139, 305, 29);
		panel_Affine.add(lbl_Affine_Key1);
		
		textField_Affine_Key1 = new JTextField();
		textField_Affine_Key1.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_Affine_Key1.setColumns(10);
		textField_Affine_Key1.setBounds(10, 172, 305, 35);
		panel_Affine.add(textField_Affine_Key1);
		
		JLabel lbl_Affine_Key2 = new JLabel("输入密钥2：");
		lbl_Affine_Key2.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Affine_Key2.setBounds(336, 139, 305, 29);
		panel_Affine.add(lbl_Affine_Key2);
		
		textField_Affine_Key2 = new JTextField();
		textField_Affine_Key2.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_Affine_Key2.setColumns(10);
		textField_Affine_Key2.setBounds(336, 172, 305, 35);
		panel_Affine.add(textField_Affine_Key2);
		
		JLabel lbl_Affine_Result = new JLabel("结果：");
		lbl_Affine_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Affine_Result.setBounds(10, 228, 108, 29);
		panel_Affine.add(lbl_Affine_Result);
		
		JScrollPane scrollPane_Affine = new JScrollPane();
		scrollPane_Affine.setBounds(10, 263, 815, 101);
		panel_Affine.add(scrollPane_Affine);
		
		textArea_Affine = new JTextArea();
		textArea_Affine.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane_Affine.setViewportView(textArea_Affine);
		
		JButton btn_Affine_En = new JButton("加密");
		btn_Affine_En.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Affine a = new Affine();
				String strk1 = textField_Affine_Key1.getText();
				String strk2 = textField_Affine_Key2.getText();
				int k1 = 0,k2 = 0;
				try {
					k1 = Integer.valueOf(strk1);
					k2 = Integer.valueOf(strk2);
				}catch(Exception e_Affine){
					textArea_Affine.setText("Invalid!");
				}

				String plain = textField_Affine_Str.getText();
				String result = ""; 
		        if((k1 == 1||k1 == 3||k1 == 5||k1 == 7
		        		||k1 == 9||k1 == 11||k1 == 15||
		        		k1 == 17||k1 == 19||k1 == 21||k1 == 23||k1 == 25)&&(0<k2&&k2<=25)) {
		            result = a.encryption(plain,k1,k2);
		        }else {
					result = "Invalid!";
		        }
		        textArea_Affine.setText(result);
			}
		});
		btn_Affine_En.setBackground(new Color(153, 204, 153));
		btn_Affine_En.setBounds(672, 94, 153, 37);
		panel_Affine.add(btn_Affine_En);
		
		JButton btn_Affine_De = new JButton("解密");
		btn_Affine_De.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Affine a = new Affine();
				String strk1 = textField_Affine_Key1.getText();
				String strk2 = textField_Affine_Key2.getText();
				int k1 = 0,k2 = 0;
				try {
					k1 = Integer.valueOf(strk1);
					k2 = Integer.valueOf(strk2);
				}catch(Exception e_Affine){
					textArea_Affine.setText("Invalid!");
				}
				String cipher = textField_Affine_Str.getText();
				String result = ""; 
		        if((k1 == 1||k1 == 3||k1 == 5||k1 == 7
		        		||k1 == 9||k1 == 11||k1 == 15||
		        		k1 == 17||k1 == 19||k1 == 21||k1 == 23||k1 == 25)&&(0<k2&&k2<=25)) {
		            result = a.decryption(cipher,k1,k2);
		        }else {
					result = "Invalid!";
		        }
		        textArea_Affine.setText(result);
			}
		});
		btn_Affine_De.setBackground(new Color(204, 255, 255));
		btn_Affine_De.setBounds(672, 172, 153, 37);
		panel_Affine.add(btn_Affine_De);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_Affine_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1.setBounds(85, 224, 129, 37);
		panel_Affine.add(btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1);
		
//DES界面	
		JPanel panel_DES = new JPanel();
		panel_DES.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_DES, "DES");
		panel_DES.setLayout(null);
		
		JLabel lbl_DES = new JLabel("DES");
		lbl_DES.setFont(new Font("Bradley Hand ITC", Font.BOLD, 50));
		lbl_DES.setBounds(312, 0, 225, 46);
		panel_DES.add(lbl_DES);
		
		JLabel lbl_DES_Str = new JLabel("输入字符串：");
		lbl_DES_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DES_Str.setBounds(21, 41, 200, 29);
		panel_DES.add(lbl_DES_Str);
		
		textField_DES_Str = new JTextField();
		textField_DES_Str.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_DES_Str.setBounds(21, 79, 581, 35);
		panel_DES.add(textField_DES_Str);
		textField_DES_Str.setColumns(10);
				
		JLabel lbl_DES_Key = new JLabel("输入七位密钥：");
		lbl_DES_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DES_Key.setBounds(21, 122, 200, 29);
		panel_DES.add(lbl_DES_Key);
		
		textField_DES_Key = new JTextField();
		textField_DES_Key.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String str = textField_DES_Key.getText();
				if(str.length() >7 ) {
					e.consume();
				}
			}
		});
		textField_DES_Key.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_DES_Key.setColumns(10);
		textField_DES_Key.setBounds(21, 160, 581, 35);
		panel_DES.add(textField_DES_Key);
		
		JLabel lbl_DES_Result = new JLabel("结果：");
		lbl_DES_Result.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_DES_Result.setBounds(21, 216, 108, 29);
		panel_DES.add(lbl_DES_Result);
		
		JScrollPane scrollPane_DES = new JScrollPane();
		scrollPane_DES.setBounds(21, 255, 793, 109);
		panel_DES.add(scrollPane_DES);

		textArea_DES = new JTextArea();
		textArea_DES.setLineWrap(true);        	// 激活自动换行功能 
		textArea_DES.setWrapStyleWord(true);		// 激活断行不断字功能
		textArea_DES.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane_DES.setViewportView(textArea_DES);
		
		JButton btn_DES_EN = new JButton("加密");
		btn_DES_EN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Encryption encry = new Encryption();
				Decryption decry = new Decryption();
				String str = "";
				mainDES m = new mainDES();
				try {
					String keystr = textField_DES_Key.getText();
					keystr = m.filter(keystr);
	
					char []key = keystr.toCharArray();
	
					String plainstr = textField_DES_Str.getText();
					if(plainstr.length() > 8) {
						str = "Invalid!";
						textArea_DES.setText(str);
					}else {
						plainstr = m.filter(plainstr);
						
						char []plaintext = plainstr.toCharArray();
						
						str = encry.encrypt(plaintext, key);
						textArea_DES.setText(str);
				}
				}catch(Exception e_DES) {
					textArea_DES.setText("Invalid");
				}
				
		
			}
		});
		btn_DES_EN.setBackground(new Color(153, 204, 153));
		btn_DES_EN.setBounds(661, 78, 153, 37);
		panel_DES.add(btn_DES_EN);
		
		JButton btn_DES_DE = new JButton("解密");
		btn_DES_DE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Encryption encry = new Encryption();
				Decryption decry = new Decryption();
				
				mainDES m = new mainDES();
				String keystr = textField_DES_Key.getText();
				keystr = m.filter(keystr);

				char []key = keystr.toCharArray();

				String cipherStr = textField_DES_Str.getText();
				cipherStr = m.filter(cipherStr);
				
				char []plaintext = cipherStr.toCharArray();
				
				char []cipher = textField_DES_Str.getText().toCharArray();
				String str = decry.decrypt(cipher, key);
				
				textArea_DES.setText(m.BinstrToStr(str));
			}
		});
		btn_DES_DE.setBackground(new Color(204, 255, 255));
		btn_DES_DE.setBounds(661, 159, 153, 37);
		panel_DES.add(btn_DES_DE);
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1_1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = readFile();
				textField_DES_Str.setText(str);
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1_1.setBackground(new Color(255, 204, 153));
		btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1_1.setBounds(99, 212, 129, 37);
		panel_DES.add(btnNewButton_1_1_1_1_1_1_1_1_1_1_1_1_1_1_1);

//初始页面			
		JPanel panel_Front = new JPanel();
		panel_Front.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_Front, "Front");
		panel_Front.setLayout(null);
	
		JLabel lbl_Front_Notice = new JLabel("欢迎进入单机加密系统！");
		lbl_Front_Notice.setFont(new Font("宋体", Font.BOLD, 50));
		lbl_Front_Notice.setBounds(143, 78, 592, 169);
		panel_Front.add(lbl_Front_Notice);
		
		JLabel lbl_Front_Tips = new JLabel("请点击菜单中选项选择加解密算法");
		lbl_Front_Tips.setFont(new Font("宋体", Font.PLAIN, 25));
		lbl_Front_Tips.setBounds(225, 248, 390, 29);
		panel_Front.add(lbl_Front_Tips);
		
		//监听器部分，用于捕获点击menuItem的信息，进而跳转页面
		cardLayout.show(frame.getContentPane(),"Front");	
		ActionListener  listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String actionCommand = e.getActionCommand();
				switch(actionCommand) {
				case "Caesar Cipher":
					cardLayout.show(frame.getContentPane(),"Caesar");
					break;
				case "Keyword Cipher":
					cardLayout.show(frame.getContentPane(),"Keyword");
					break;
				case "Multiliteral Cipher":
					cardLayout.show(frame.getContentPane(),"Multiliteral");
					break;
				case "RC4":
					cardLayout.show(frame.getContentPane(),"RC4");
					break;
				case "CA":
					cardLayout.show(frame.getContentPane(),"CA");
					break;
				case "RSA":
					cardLayout.show(frame.getContentPane(),"RSA");
					break;
				case "MD5":
					cardLayout.show(frame.getContentPane(),"MD5");
					break;
				case "DH":
					cardLayout.show(frame.getContentPane(),"DH");
					break;
				case "Vigenere Cipher":
					cardLayout.show(frame.getContentPane(),"Vigenere");
					break;
				case "Playfair":
					cardLayout.show(frame.getContentPane(),"Playfair Cipher");
					break;
				case "Autokey Ciphertext":
					cardLayout.show(frame.getContentPane(),"Autokey Ciphertext");
					break;
				case "Autokey Plaintext":
					cardLayout.show(frame.getContentPane(),"Autokey Plaintext");
					break;
				case "Affine Cipher":
					cardLayout.show(frame.getContentPane(),"Affine");
					break;
				case "Permutation Cipher":
					cardLayout.show(frame.getContentPane(),"Permutation");
					break;
				case "Column Permutation":
					cardLayout.show(frame.getContentPane(),"Column Permutation");
					break;
				case "Double Transposition":
					cardLayout.show(frame.getContentPane(),"Double Transposition");
					break;
				case "DES":
					cardLayout.show(frame.getContentPane(),"DES");
					break;
				}
			}
		};
		
		//将menuItem添加监听器
		menuItem_Caesar.addActionListener(listener);
		menuItem_Keyword.addActionListener(listener);
		menuItem_Affine.addActionListener(listener);
		menuItem_Multiliteral.addActionListener(listener);
		menuItem_RC4.addActionListener(listener);
		menuItem_CA.addActionListener(listener);
		menuItem_RSA.addActionListener(listener);
		menuItem_MD5.addActionListener(listener);
		menuItem_DH.addActionListener(listener);
		menuItem_Vigenere.addActionListener(listener);
		menuItem_AutokeyCiphertext.addActionListener(listener);
		menuItem_AutokeyPlaintext.addActionListener(listener);
		menuItem_PermutationCipher.addActionListener(listener);
		menuItem_Column.addActionListener(listener);
		menuItem_DoubleTransposition.addActionListener(listener);
		menuItem_Playfair.addActionListener(listener);
		menuItem_DES.addActionListener(listener);
	}
}
