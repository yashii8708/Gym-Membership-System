import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Member {
    int id;
    String name, gender, membership;
    double fees;

    Member(int id, String name, String gender, String membership, double fees) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.membership = membership;
        this.fees = fees;
    }
}

public class GymSystem implements ActionListener {

    JFrame frame;
    JTextField idField, nameField, searchField;
    JComboBox<String> genderBox, membershipBox;
    JButton addBtn, deleteBtn, searchBtn, expireBtn;
    JTable table;
    DefaultTableModel model;
    ArrayList<Member> list = new ArrayList<>();

    GymSystem() {

        frame = new JFrame("Gym Membership System - By Yashi");
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("GYM MEMBERSHIP SYSTEM");
        title.setBounds(180, 10, 400, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title);

        JLabel id = new JLabel("Member ID:");
        id.setBounds(50, 70, 100, 25);
        frame.add(id);

        idField = new JTextField();
        idField.setBounds(150, 70, 150, 25);
        frame.add(idField);

        JLabel name = new JLabel("Name:");
        name.setBounds(50, 110, 100, 25);
        frame.add(name);

        nameField = new JTextField();
        nameField.setBounds(150, 110, 150, 25);
        frame.add(nameField);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(50, 150, 100, 25);
        frame.add(gender);

        String g[] = {"Male", "Female"};
        genderBox = new JComboBox<>(g);
        genderBox.setBounds(150, 150, 150, 25);
        frame.add(genderBox);

        JLabel mem = new JLabel("Membership:");
        mem.setBounds(50, 190, 100, 25);
        frame.add(mem);

        String m[] = {"Monthly", "Quarterly", "Yearly"};
        membershipBox = new JComboBox<>(m);
        membershipBox.setBounds(150, 190, 150, 25);
        frame.add(membershipBox);

        addBtn = new JButton("Add Member");
        addBtn.setBounds(80, 250, 150, 30);
        addBtn.addActionListener(this);
        frame.add(addBtn);

        deleteBtn = new JButton("Delete Member");
        deleteBtn.setBounds(250, 250, 150, 30);
        deleteBtn.addActionListener(this);
        frame.add(deleteBtn);

        searchField = new JTextField();
        searchField.setBounds(320, 70, 120, 25);
        frame.add(searchField);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(450, 70, 100, 25);
        searchBtn.addActionListener(this);
        frame.add(searchBtn);

        expireBtn = new JButton("Expire Membership");
        expireBtn.setBounds(420, 250, 180, 30);
        expireBtn.addActionListener(this);
        frame.add(expireBtn);

        // TABLE
        String[] cols = {"ID", "Name", "Gender", "Membership", "Fees", "Status"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 300, 600, 150);
        frame.add(sp);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        // ADD
        if (e.getSource() == addBtn) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String membership = (String) membershipBox.getSelectedItem();

            double fees;
            if (membership.equals("Monthly")) fees = 1000;
            else if (membership.equals("Quarterly")) fees = 2500;
            else fees = 8000;

            Member m = new Member(id, name, gender, membership, fees);
            list.add(m);

            model.addRow(new Object[]{id, name, gender, membership, fees, "Active"});

            JOptionPane.showMessageDialog(frame, "Member Added!");
        }

        // DELETE
        if (e.getSource() == deleteBtn) {
            int id = Integer.parseInt(idField.getText());

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).id == id) {
                    list.remove(i);
                    model.removeRow(i);
                    JOptionPane.showMessageDialog(frame, "Member Deleted!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Member not found!");
        }

        // SEARCH
        if (e.getSource() == searchBtn) {
            int id = Integer.parseInt(searchField.getText());

            for (Member m : list) {
                if (m.id == id) {
                    JOptionPane.showMessageDialog(frame,
                            "Name: " + m.name +
                            "\nMembership: " + m.membership +
                            "\nFees: " + m.fees);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Member not found!");
        }

        // EXPIRE
        if (e.getSource() == expireBtn) {
            int id = Integer.parseInt(searchField.getText());

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).id == id) {
                    model.setValueAt("Expired", i, 5);
                    JOptionPane.showMessageDialog(frame, "Membership Expired!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Member not found!");
        }
    }

    public static void main(String[] args) {
        new GymSystem();
    }
}