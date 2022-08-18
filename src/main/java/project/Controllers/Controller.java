package project.Controllers;

import facade.Facade;
import project.Model.*;
import project.Model.Employees.*;
import project.Utilities;
import project.Views.*;
import project.review.ComparisonPrinter;
import project.Views.ViewCreateReview;
import project.review.PrintReviewManager;

import java.util.Collection;

public class Controller {
    private Model mModel;
    private Facade mFacade;
    private ViewMainMenu mMainMenuView;
    ViewItemOptions mItemOptionsView;
    ViewReviewsOptions mReviewOptionsView;
    ViewTransactionHistoryOptions mTransactionHistoryOptionsView;
    ViewCreateEmployee mCreateEmployeeView;
    ViewCreateItem mCreateItemView;
    ViewEmployeeData mEmployeeDataView;
    ViewUpdateItem mUpdateItemView;
    ViewBuyItem mBuyItemView;
    ViewRemoveEmployee mRemoveEmployeeView;
    ViewRemoveItem mRemoveItemView;
    ComparisonPrinter mComparisonPrinter;
    PrintReviewManager mPrintReviewManager;
    ViewEmployeeOptionsMenu mViewEmployeeOptions;
    ViewTotalProfit mTotalProfitView;
    ViewAllTransactions mAllTransactionsView;

    public Controller() {
        mModel = new Model();
        mFacade = new Facade();

        mMainMenuView = new ViewMainMenu();
        mItemOptionsView = new ViewItemOptions();
        mReviewOptionsView = new ViewReviewsOptions();
        mTransactionHistoryOptionsView = new ViewTransactionHistoryOptions();
        mCreateEmployeeView = new ViewCreateEmployee();
        mCreateItemView = new ViewCreateItem();
        mEmployeeDataView = new ViewEmployeeData();
        mPrintReviewManager = new PrintReviewManager();
        mComparisonPrinter = new ComparisonPrinter();
        mUpdateItemView = new ViewUpdateItem();
        mBuyItemView = new ViewBuyItem();
        mViewEmployeeOptions = new ViewEmployeeOptionsMenu();
        mRemoveEmployeeView = new ViewRemoveEmployee();
        mRemoveItemView = new ViewRemoveItem();
        mTotalProfitView = new ViewTotalProfit();
        mAllTransactionsView = new ViewAllTransactions();

    }

    public void main() {
        //starts the system and instantiates main method
        doMainMenu();
    }

    void doMainMenu() {
        mMainMenuView.renderMenu();

        int mainMenuSelect = mMainMenuView.readInput();

        switch (mainMenuSelect) {
            case 0:
                //close system
                mMainMenuView.renderExit();
                Utilities.closeScanner();
                break;

            case 1:
                // open item options
                doItemOptionsMenu();
                break;

            case 2:
                //open review options
                doReviewsOptionsMenu();
                break;

            case 3:
                //open transaction history options
                doTransactionHistoryMenu();
                break;

            case 4:
                //open employee options menu
                try {
                    doEmployeeOptionsMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                mMainMenuView.renderError();
                doMainMenu();
                break;
        }
    }

    void doItemOptionsMenu() {
        mItemOptionsView.renderItemOptions();

        int itemOptionsInput = mItemOptionsView.itemOptionsInput();

        Item item;
        String ID = null;

        switch (itemOptionsInput) {
            case 0:
                //Return to main menu
                mItemOptionsView.returnMainMenu();
                doMainMenu();
                break;

            case 1:
                //Create an item
                ID = mCreateItemView.itemId();
                String name = mCreateItemView.itemName();
                Double price = mCreateItemView.price();

                mFacade.createItem(ID, name, price);
                doItemOptionsMenu();
                break;
            case 2:
                //Remove an item
                mRemoveItemView.promptRemoveItem();
                ID = mRemoveItemView.removeItem();

                System.out.println(mFacade.removeItem(ID));

                doItemOptionsMenu();
                break;
            case 3:
                //Print all registered items
                System.out.println(mFacade.printAllItems());
                doItemOptionsMenu();
                break;

            case 4:
                //Buy an item
                ID = mBuyItemView.buyItemPrompt();
                int amount = mBuyItemView.promptAmount();

                mFacade.buyItem(ID, amount);
                doItemOptionsMenu();
                break;

            case 5:
                //Update an item's name
                ID = mUpdateItemView.updateItemPrompt();
                name = mUpdateItemView.newItemName();

                System.out.println(mFacade.updateItemName(ID, name));
                doItemOptionsMenu();
                break;

            case 6:
                //Update an item's price
                ID = mUpdateItemView.updateItemPrompt();
                price = mUpdateItemView.newItemPrice();

                System.out.println(mFacade.updateItemPrice(ID, price));
                doItemOptionsMenu();
                break;

            case 7:
                //Print a specific item
                ID = mUpdateItemView.updateItemPrompt();
                System.out.println(mFacade.printItem(ID));
                doItemOptionsMenu();
                break;
        }
    }

    void doReviewsOptionsMenu() {
        mReviewOptionsView.reviewsOptionsMenu();

        int reviewOptionsInput = mReviewOptionsView.reviewsOptionsInput();

        switch (reviewOptionsInput) {
            case 0:
                //Return to main menu
                mReviewOptionsView.returnMainMenu();
                doMainMenu();
                break;

            case 1:
                ViewCreateReview viewCreateReview = new ViewCreateReview(mModel);
                viewCreateReview.createReview();
                doReviewsOptionsMenu();
                break;
            case 2:
                mPrintReviewManager.printReview(mModel);
                //Print a specific review of an Item.
                doReviewsOptionsMenu();
                break;
            case 3:
                mPrintReviewManager.printAll(mModel);
                //Print all reviews of an Item.
                doReviewsOptionsMenu();
                break;
            case 4:
                //Print mean grade of an Item
                mPrintReviewManager.meanGrade(mModel);
                doReviewsOptionsMenu();
                break;
            case 5:
                //Print all comments of an Item.
                mPrintReviewManager.printComments(mModel);
                doReviewsOptionsMenu();
                break;
            case 6:
                mComparisonPrinter.everyReview(mModel);
                //Print all registered reviews.
                doReviewsOptionsMenu();
                break;

            case 7:
                mComparisonPrinter.mostReviews(mModel);
                //Print item(s) with most reviews.
                doReviewsOptionsMenu();
                break;
            case 8:
                mComparisonPrinter.leastReviews(mModel);
                //Print item(s) with least reviews.
                doReviewsOptionsMenu();
                break;
            case 9:
                mComparisonPrinter.bestRating(mModel);
                //Print item(s) with best mean review grade.
                doReviewsOptionsMenu();
                break;
            case 10:
                mComparisonPrinter.worstRating(mModel);
                //Print item(s) with worst mean review grade.
                doReviewsOptionsMenu();
                break;
        }
    }

    void doTransactionHistoryMenu() {
        mTransactionHistoryOptionsView.transactionHistoryOptions();

        int transactionHistoryOptionsInput = mTransactionHistoryOptionsView.transactionHistoryOptionsInput();

        String ID;

        switch (transactionHistoryOptionsInput) {
            case 0:
                //Return to main menu
                mTransactionHistoryOptionsView.returnMainMenu();

                doMainMenu();
                break;
            case 1:
                //Print total profit from all item purchases
                System.out.println(mFacade.getTotalProfit());

                doTransactionHistoryMenu();
                break;
            case 2 :
                //Print total units sold from all item purchases
                System.out.println(mFacade.getTotalUnitsSold());

                doTransactionHistoryMenu();
                break;
            case 3 :
                //Print the total number of item transactions made.
                ID = mTotalProfitView.promptItemSearch();
                System.out.println(mFacade.printItemTransactions(ID));

                doTransactionHistoryMenu();
                break;
            case 4 :
                //Print all transactions made.
                System.out.println(mFacade.getTotalTransactions());

                doTransactionHistoryMenu();
                break;
            case 5 :
                //Print the total profit of a specific item.
                ID = mTotalProfitView.promptItemSearch();
                System.out.println(mFacade.getProfit(ID));

                doTransactionHistoryMenu();
                break;
            case 6:
                //Print the number of units sold of a specific item.
                ID = mTotalProfitView.promptItemSearch();
                System.out.println(mFacade.getUnitsSolds(ID));

                doTransactionHistoryMenu();
                break;
            case 7 :
                //Print all transactions of a specific item.
                ID = mTotalProfitView.promptItemSearch();
                System.out.println(mFacade.printItemTransactions(ID));

                doTransactionHistoryMenu();
                break;
            case 8 :
                //Print item with highest profit.
                System.out.println(mFacade.printMostProfitableItems());

                doTransactionHistoryMenu();
                break;
            default:
                doMainMenu();
                break;
        }
    }

    public void doEmployeeOptionsMenu() throws Exception {
        mViewEmployeeOptions.renderEmployeeOptions();

        int employeeOptionsInput = mViewEmployeeOptions.employeeOptionsInput();
        String id = null;
        String name;
        double salary;

        switch (employeeOptionsInput) {

            case 0:
                //Return to main menu
                mViewEmployeeOptions.returnMainMenu();
                doMainMenu();
                break;
            case 1:
                //make Employee
                id = mCreateEmployeeView.employeeId();
                name = mCreateEmployeeView.employeeName();
                salary = mCreateEmployeeView.employeeSalary();

                try {
                    mFacade.createEmployee(id, name, salary);
                } catch (Exception e) {
                    System.out.println(e.getMessage());;
                }

                doEmployeeOptionsMenu();
                break;
            case 2:
                //make Manager
                id = mCreateEmployeeView.employeeId();
                name = mCreateEmployeeView.employeeName();
                salary = mCreateEmployeeView.employeeSalary();
                DegreeType degreeType = mCreateEmployeeView.chooseDegree();

                try {
                    mFacade.createEmployee(id, name, salary, degreeType.toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 3:
                //make Director
                id = mCreateEmployeeView.employeeId();
                name = mCreateEmployeeView.employeeName();
                salary = mCreateEmployeeView.employeeSalary();
                degreeType = mCreateEmployeeView.chooseDegree();
                String dept = mCreateEmployeeView.directorDept();

                try {
                    mFacade.createEmployee(id, name, salary, degreeType.toString(), dept);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 4:
                //make Intern
                id = mCreateEmployeeView.employeeId();
                name = mCreateEmployeeView.employeeName();
                salary = mCreateEmployeeView.employeeSalary();
                Integer gpa = mCreateEmployeeView.internGpa();

                try {
                    mFacade.createEmployee(id, name, salary, gpa);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 5:
                //removes an employee
                mRemoveEmployeeView.promptRemoveEmployee();
                id = mRemoveEmployeeView.removeEmp();

                try {
                    System.out.println(mFacade.removeEmployee(id));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 6:
                //print specific employee
                id = mEmployeeDataView.inputEmployeeId();

                try {
                    System.out.println(mFacade.printEmployee(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 7:
                //print all registered employees
                try {
                    System.out.println(mFacade.printAllEmployees());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 8:
                //print total expense with net salary
                try {
                    System.out.println(mFacade.getTotalNetSalary());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                doEmployeeOptionsMenu();
                break;
            case 9:
                //print all employees sorted by gross salary
                try {
                    System.out.println(mFacade.printSortedEmployees());
                } catch (Exception e) {
                    System.out.println(e.getMessage());;
                }

                doEmployeeOptionsMenu();
                break;
        }
    }

}
