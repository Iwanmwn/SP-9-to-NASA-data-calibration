package com.myowndev.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.myowndev.fileIO.Load_CIMEL;
import com.myowndev.fileIO.Load_SP9;
import com.myowndev.fileIO.Save_Calibrated;

public class Panel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<String> tSP9Time = new ArrayList<String>();
	public ArrayList<String> tCimelTime = new ArrayList<String>();
	public ArrayList<Float> tSP9Data = new ArrayList<Float>();
	public ArrayList<Float> tCimelData = new ArrayList<Float>();
	
	public JComboBox<String> chan_cb = new JComboBox<String>();
	public JComboBox<String> day_cb = new JComboBox<String>();
	public JComboBox<String> time_cb = new JComboBox<String>();
	public JFrame frame = new JFrame();
	
	public JButton button_calculate = new JButton();
	public JButton button_load_sp9 = new JButton();
	public JButton button_load_cimel = new JButton();
	public JButton button_change_time = new JButton();
	public JButton button_save_calibrated_file = new JButton();
	public JButton button_show_graph = new JButton();
	public JTextArea tarea = new JTextArea(16, 58);
	public Font tarea_font = new Font("Times New Roman", Font.PLAIN, 12);
	public JLabel label_1 = new JLabel("Графическое представление данных по дням и каналам (дни по СП-9): ");
	public JLabel label_2 = new JLabel("Перевод времени: ");
	
	private static class Line {
		final int x1;
		final int y1;
		final int x2;
		final int y2;
		final Color color;
		public Line(int x1, int y1, int x2, int y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
		}
	}
	private final LinkedList<Line> lines = new LinkedList<Line>();
	public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}
	public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(new Line(x1,x2,x3,x4, color));
		repaint();
	}
	public void clearLines() {
		lines.clear();
		repaint();
	}

	private int temp = 0;
	private int lastx = 10;
	private int lasty = 650;
	private int tempx;
	private int tempy;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(10, 650, 480, 650); // ось x
		g.drawLine(10, 650, 10, 400); // ось y
		for(int i = 0; i < 24; i++) {
			temp = temp + (int) 19.58;
			g.drawLine(10 + temp, 648, 10 + temp, 652);
		} temp = 0;
		for(int i = 0; i < 12; i++) {
			temp = temp + (int) 20.83;
			g.drawLine(8, 650 - temp, 12, 650 - temp);
		} temp = 0;
		for(int i = 0; i < 26; i++) {
			if (i != 25) {
				g.drawString("" + (i), 5 + temp, 665);
				temp = temp + (int) 19.58;
			} else {
				g.drawString("Время, ч.", 14 + temp, 664);
				g.setColor(Color.RED);
				g.drawString("SP-9", 14 + temp, 644);
				g.setColor(Color.BLUE);
				g.drawString("Cimel", 14 + temp, 624);
			}
		} temp = 0;
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
	}
	
	public Panel() {
		setBorder(new TitledBorder(new EtchedBorder(), "Интерфейс калибровки "));
		add(tarea);
		tarea.setLineWrap(false); // не понял что это за метод, но хочу чтобы тареа не меняла размер постоянно когда хочешь написать какой-нибудь текст, который выходит за границы поля тареа.
		tarea.setEditable(false);
		tarea.setFont(tarea_font);
		tarea.setAutoscrolls(true);
		//tarea.
		
		add(button_load_sp9);
		add(button_load_cimel);
		add(button_calculate);
		add(label_2);
		add(time_cb);
			time_cb.addItem("Не менять");
			for(int ii = 0; ii < 49; ii++) {
				if (ii < 24) time_cb.addItem("+" + (ii + 1));
				if (ii > 24) time_cb.addItem("-" + (ii - 24));
			}
		add(button_change_time);
		add(button_save_calibrated_file);
		add(label_1);
		add(day_cb);
			day_cb.addItem("Выберите день");
		add(chan_cb);
			for(int ii = 1; ii < 18; ii++) {
				chan_cb.addItem("Канал " + ii);
			}
		add(button_show_graph);
		
		button_load_sp9.setText("Загрузить данные SP9");
		button_load_cimel.setText("Загрузить данные Cimel");
		button_calculate.setText("Рассчитать данные");
		button_show_graph.setText("Вывести график");
		button_change_time.setText("Изменить время");
		button_save_calibrated_file.setText("Сохранить откалиброванные данные в отдельный файл");
		
		button_calculate.addActionListener(this);
		button_load_sp9.addActionListener(this);
		button_load_cimel.addActionListener(this);
		button_show_graph.addActionListener(this);
		button_change_time.addActionListener(this);
		button_save_calibrated_file.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_load_sp9) {
			JFileChooser fChooser_SP9 = new JFileChooser();
			int fChooserValue_SP9 = fChooser_SP9.showOpenDialog(this);
			if(fChooserValue_SP9 == JFileChooser.APPROVE_OPTION) {
				Load_SP9.filedestination = fChooser_SP9.getSelectedFile().getPath();
				tarea.append("Данные SP9 загружены.\n");
				new Load_SP9();
				String prevDate = "previousDateIsActuallyATemporaryStringForLoopThatLiesOneStrokeBelow"; // Если это будет слишком длинным, а тем более еще и непонятным, заменить или убрать этот стринг
				for (int i = 0; i < Load_SP9.DATE.size(); i++) {
					if(!Load_SP9.DATE.get(i).equals(prevDate) && !Load_SP9.DATE.get(i).equals("16165165")) {
						day_cb.addItem(Load_SP9.DATE.get(i));
						prevDate = Load_SP9.DATE.get(i);
					}
				}
			}
		}
		if(e.getSource() == button_load_cimel) {
			JFileChooser fChooser_Cimel = new JFileChooser();
			int fChooserValue_Cimel = fChooser_Cimel.showOpenDialog(this);
			if(fChooserValue_Cimel == JFileChooser.APPROVE_OPTION) {
				Load_CIMEL.filedestination = fChooser_Cimel.getSelectedFile().getPath();
				tarea.append("Данные Cimel CE-318 загружены.\n");
				new Load_CIMEL();
			}
		}
		if(e.getSource() == button_change_time) {
			// Перевод на 24 часа вперед осуществлен
			// Возможно перевод в отрицательную сторону невозможен
			String temp = null;
			for (int i = 0; i < Load_SP9.K2.size(); i++) {
				Load_SP9.TIME.set(i, Integer.toString((Integer.parseInt(Load_SP9.TIME.get(i).substring(0, 2)) + time_cb.getSelectedIndex())) + Load_SP9.TIME.get(i).substring(2));
				if(Load_SP9.TIME.get(i).length() != 6) {
					Load_SP9.TIME.set(i, "0" + Load_SP9.TIME.get(i));
				}
				if(Integer.parseInt(Load_SP9.TIME.get(i).substring(0, 2)) > 24) {
					Load_SP9.TIME.set(i, Integer.toString((Integer.parseInt(Load_SP9.TIME.get(i).substring(0, 2)) - 24)) + Load_SP9.TIME.get(i).substring(2));
					if(Load_SP9.TIME.get(i).length() != 6) {
						Load_SP9.TIME.set(i, "0" + Load_SP9.TIME.get(i));
					}
					int ii = i;
					temp = Load_SP9.DATE.get(i);
					while (temp == Load_SP9.DATE.get(i)) {
						if (Load_SP9.DATE.get(ii) != temp) {
							Load_SP9.DATE.set(i, Load_SP9.DATE.get(ii));
						}
						ii++;
					}
				}
			} temp = null;
			for(int i = 0; i < Load_CIMEL.AOT_412.size(); i++) {
				Load_CIMEL.TIME.set(i, Integer.toString((Integer.parseInt(Load_CIMEL.TIME.get(i).substring(0, 2)) + time_cb.getSelectedIndex())) + Load_CIMEL.TIME.get(i).substring(2));
				if(Load_CIMEL.TIME.get(i).length() != 6) {
					Load_CIMEL.TIME.set(i, "0" + Load_CIMEL.TIME.get(i));
				}
				if(Integer.parseInt(Load_CIMEL.TIME.get(i).substring(0, 2)) > 24) {
					Load_CIMEL.TIME.set(i, Integer.toString((Integer.parseInt(Load_CIMEL.TIME.get(i).substring(0, 2)) - 24)) + Load_CIMEL.TIME.get(i).substring(2));
					if(Load_CIMEL.TIME.get(i).length() != 6) {
						Load_CIMEL.TIME.set(i, "0" + Load_CIMEL.TIME.get(i));
					}
					temp = Load_CIMEL.DATE.get(i);
					for (int ii = i; ii < Load_CIMEL.AOT_412.size(); ii++) {
						if (Load_CIMEL.DATE.get(ii) != temp) {
							Load_CIMEL.DATE.set(i, Load_CIMEL.DATE.get(ii));
						}
					}
				}
			}
			
		}
		if(e.getSource() == button_calculate) {
			new Calculator();
			tarea.append("Все даные рассчитаны, можно работать с графиком.\n");
		}
		if(e.getSource() == button_save_calibrated_file) {
			new Save_Calibrated();
			tarea.append("Данные сохранены.\n");
		}
		if(e.getSource() == button_show_graph) {
			clearLines();
			tSP9Time.clear();
			tSP9Data.clear();
			tCimelTime.clear();
			tCimelData.clear();
			//tarea.append("График показан. День " + day_cb.getSelectedItem() + ", " + chan_cb.getSelectedItem() + "\n"); // Лучше убрать отчет о том, что на график что-то вывелось потому что интенсивный просмотр графиков засоряет тарею
			Color clr;
			clr = new Color(255, 0, 0);
			for(int i = 0; i < Load_SP9.K2.size(); i++) {
				if(Load_SP9.DATE.get(i).equals(day_cb.getSelectedItem())) {
					tSP9Time.add(Load_SP9.TIME.get(i));
					if (chan_cb.getSelectedItem().equals("Канал 1")) {
						tSP9Data.add(Load_SP9.K1.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 2")) {
						tSP9Data.add(Load_SP9.K2.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 3")) {
						tSP9Data.add(Load_SP9.K3.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 4")) {
						tSP9Data.add(Load_SP9.K4.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 5")) {
						tSP9Data.add(Load_SP9.K5.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 6")) {
						tSP9Data.add(Load_SP9.K6.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 7")) {
						tSP9Data.add(Load_SP9.K7.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 8")) {
						tSP9Data.add(Load_SP9.K8.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 9")) {
						tSP9Data.add(Load_SP9.K9.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 10")) {
						tSP9Data.add(Load_SP9.K10.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 11")) {
						tSP9Data.add(Load_SP9.K11.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 12")) {
						tSP9Data.add(Load_SP9.K12.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 13")) {
						tSP9Data.add(Load_SP9.K13.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 14")) {
						tSP9Data.add(Load_SP9.K14.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 15")) {
						tSP9Data.add(Load_SP9.K15.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 16")) {
						tSP9Data.add(Load_SP9.K16.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 17")) {
						tSP9Data.add(Load_SP9.K17.get(i));
					}
					for(int ii = 0; ii < tSP9Data.size(); ii++) {
						tempx = (int) (10 + (19.58 * (Float.parseFloat(tSP9Time.get(ii).substring(0, 2)) + Float.parseFloat((tSP9Time.get(ii).substring(2, 4)))/60)));
						tempy = (int) (650 - (20.83 * 10 * (tSP9Data.get(ii)))); // умножение на десять для теста. Убрать в будущем.
						addLine(tempx, tempy, lastx, lasty, clr);
						lastx = tempx;
						lasty = tempy;
					} tempx = 10; tempy = 650; lastx = 10; lasty = 650;
				}
			}
			clr = new Color(0, 0, 255);
			for(int i = 0; i < Load_CIMEL.AOT_412.size(); i++) {
				if(Load_CIMEL.DATE.get(i).equals(day_cb.getSelectedItem())) {
					tCimelTime.add(Load_CIMEL.TIME.get(i));
					if(chan_cb.getSelectedItem().equals("Канал 1")) {
						tCimelData.add(Load_CIMEL.AOT_412.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 2")) {
						tCimelData.add(Load_CIMEL.AOT_440.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 3")) {
						tCimelData.add(Load_CIMEL.AOT_500.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 4")) {
						tCimelData.add(Load_CIMEL.AOT_551.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 5")) {
						tCimelData.add(Load_CIMEL.AOT_675.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 6")) {
						tCimelData.add(Load_CIMEL.AOT_1640.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 7")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 8")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 9")) {
						tCimelData.add(Load_CIMEL.AOT_870.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 10")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 11")) {
						tCimelData.add(Load_CIMEL.AOT_1020.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 12")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 13")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 14")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 15")) {
						i = Load_CIMEL.AOT_412.size(); tCimelData.add(Load_CIMEL.AOT_340.get(i));
					} else if(chan_cb.getSelectedItem().equals("Канал 16")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!");
					} else if(chan_cb.getSelectedItem().equals("Канал 17")) {
						i = Load_CIMEL.AOT_412.size(); tarea.append("Нет соответсвующего канала Симель для СП-9!"); // <- костыли какие-то, но разумные костыли
					}
				}
				for(int ii = 0; ii < tCimelData.size(); ii++) {
					tempx = (int) (10 + (19.58 * (Float.parseFloat(tCimelTime.get(ii).substring(0, 2)) + Float.parseFloat((tCimelTime.get(ii).substring(2, 4)))/60)));;
					tempy = (int) (650 - (20.83 * 10 * (tCimelData.get(ii)))); // такая же ситуация как с предыдущим циклом вырисовывания графики - умножение на десять здесь лишнее
					addLine(tempx, tempy, lastx, lasty, clr);
					lastx = tempx;
					lasty = tempy;
				} tempx = 10; tempy = 650; lastx = 10; lasty = 650;
			}
		}
	}

}



















































































































