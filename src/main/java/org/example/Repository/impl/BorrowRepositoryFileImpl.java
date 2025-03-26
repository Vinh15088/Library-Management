package org.example.Repository.impl;

import org.example.Repository.BorrowRepository;
import org.example.model.Borrow;
import org.example.utils.CSVFileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowRepositoryFileImpl implements BorrowRepository {

    private static final String FILE_PATH = "data/borrows.csv";
    private static final String CSV_HEADER = "id,book_id,user_id,borrow_date,return_date";

    public BorrowRepositoryFileImpl() {
        CSVFileUtils.initCSVFile(FILE_PATH, CSV_HEADER);
    }

    @Override
    public void addBorrow(Borrow borrow) {
        List<Borrow> borrows = getAllBorrows();

        if(borrows == null) {
            borrows = new ArrayList<Borrow>();
        }

        int newId = CSVFileUtils.getNextId(borrows, Borrow::getId);
        borrow.setId(newId);

        borrows.add(borrow);

        saveToFile(borrows);
    }

    @Override
    public Borrow getBorrowById(Integer id) {
        return getAllBorrows().stream().filter(borrow -> borrow.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Borrow> getBorrowsByUser(Integer userId) {
        return getAllBorrows().stream().filter(borrow -> borrow.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Borrow> getBorrowByBook(Integer bookId) {
        return getAllBorrows().stream().filter(borrow -> borrow.getBookId().equals(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return CSVFileUtils.readAllFromCSVFile(FILE_PATH, this::parseBorrowFromCSV);
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        List<Borrow> borrows = getAllBorrows();
        boolean check = false;

        for(int i = 0; i < borrows.size(); i++) {
            if(borrows.get(i).getId().equals(borrow.getId())) {
                borrows.set(i, borrow);
                check = true;
                break;
            }
        }

        if(check) {
            saveToFile(borrows);
        } else {
            System.out.println("Borrow id not found");
        }
    }

    @Override
    public void deleteBorrow(Integer id) {
        List<Borrow> borrows = getAllBorrows();
        boolean check = borrows.removeIf(borrow -> borrow.getId().equals(id));

        if(check) {
            saveToFile(borrows);
        } else {
            System.out.println("Borrow id not found");
        }
    }

    private void saveToFile(List<Borrow> borrows) {
        CSVFileUtils.saveToCSV(FILE_PATH, CSV_HEADER, borrows, this::convertBorrowToCSV);
    }

    private Borrow parseBorrowFromCSV(String line) {
        String[] values = line.split(",");

        Borrow borrow = new Borrow();
        borrow.setId(Integer.parseInt(values[0].trim()));
        borrow.setBookId(Integer.parseInt(values[1].trim()));
        borrow.setUserId(Integer.parseInt(values[2].trim()));
        borrow.setBorrowDate(LocalDate.parse(values[3].trim()));
        borrow.setReturnDate((values[4].trim().isEmpty() || values[4].trim().equals("null")) ? null : LocalDate.parse(values[4].trim()));

        return borrow;
    }

    private String convertBorrowToCSV(Borrow borrow) {
        return String.join(",",
                String.valueOf(borrow.getId()),
                String.valueOf(borrow.getBookId()),
                String.valueOf(borrow.getUserId()),
                String.valueOf(borrow.getBorrowDate()),
                String.valueOf(borrow.getReturnDate())
        );
    }
}
