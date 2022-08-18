package project.Model;

import project.Model.Employees.*;

import java.util.*;

public class Model {

    public HashMap<String, Item> mItems;
    public HashMap<String, Employee> mEmployee;
    public ArrayList<Transaction> mTransactions;

    public Model() {
        mItems = new HashMap<String, Item>();
        mEmployee = new HashMap<String, Employee>();
        mTransactions = new ArrayList<Transaction>();
    }

    public Collection<Item> getItemList() {
        return mItems.values();
    }

    public Item getItemByID(String id) {
        return mItems.get(id);
    }

    public void addItem(Item item) {
        mItems.put(item.getID(), item);
    }

    public void removeItem(String id) {
        mItems.remove(id);
    }

    public Collection<Employee> getEmployeeList() {
        return mEmployee.values();
    }

    public Employee getEmployeeID(String id) {
        return mEmployee.get(id);
    }

    public void addEmployee(Employee employee) {
        mEmployee.put(employee.getID(), employee);
    }

    public void removeEmployee(String id) {
        mEmployee.remove(id);
    }

    public void updateEmployee(Employee employee) {
        mEmployee.replace(employee.getID().toString(), employee);
    }

    //changed getAmount to getQuantity
    public Double getTotal() {
        Double totalProfit = 0.0;
        for (Transaction transactions : mTransactions) {
            totalProfit += transactions.getQuantity();
        }
        return totalProfit;
    }

    public Collection<Transaction> getTransactions() {
        ArrayList<Transaction> itemTransactions = new ArrayList<>();
        for (Transaction transactions : mTransactions) {
            itemTransactions.add(transactions);

        }
        return itemTransactions;
    }

    public String printItemTransactions(String id) {
        boolean found = false;
        Item item = getItemByID(id);
        if (null == item) {
            return "Item " + id + " was not registered yet.";
        }

        Collection<Transaction> itemTransactions = getTransactionsForItem(item);

        if(null == itemTransactions) {
            return "No transactions have been registered for item " + id + " yet.";
        }

        StringBuilder sb = new StringBuilder("Transactions for item: ");
        sb.append(item.toString());
        sb.append("\n");

        for (Transaction transaction : itemTransactions) {
            found = true;
            sb.append(transaction.toString());
            sb.append("\n");
        }

        if (!found) {
            sb.append("No transactions have been registered for item ");
            sb.append(id);
            sb.append(" yet.");
        }

        return sb.toString();
    }


    public Double getTotalNetPaid() {
        Double totalPaid = 0.00;

        Collection<Employee> employees = getEmployeeList();
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            Double netSalary = employee.getNetSalary();
            totalPaid += netSalary;
        }
        return totalPaid;
    }

    public Transaction buyItem(Item item, Integer amount) {
        Transaction transaction = item.buyItem(amount);
        mTransactions.add(transaction);
        return transaction;
    }

    public Integer getAllTransactions() {
        ArrayList<Transaction> itemTransactions = new ArrayList<>();
        for (Transaction transactions : mTransactions) {
            itemTransactions.add(transactions);
        }
        return itemTransactions.size();
    }

    public Collection<Transaction> getTransactionsForItem(Item item) {
        ArrayList<Transaction> itemTransactions = new ArrayList<>();
        for (Transaction transactions : mTransactions) {
            if (item.getID().equals(transactions.getItemId())) {
                itemTransactions.add(transactions);
            }
        }
        return itemTransactions;
    }

    public Double getProfitForItem(Item item) {
        Double profit = 0.00;
        for (Transaction transactions : mTransactions) {
            if (item.getID().equals(transactions.getItemId())) {
                profit += transactions.getTotal();
            }
        }
        return profit;
    }

    public Integer getUnitsForItem(Item item) {
        Integer units = 0;

        for (Transaction transactions : mTransactions) {
            if (item.getID().equals(transactions.getItemId())) {
                units += transactions.getQuantity();
            }
        }
        return units;
    }

    public Double getTotalProfit() {
        double totalProfit = 0.00;
        for (Transaction transactions : mTransactions) {
            totalProfit += transactions.getTotal();
        }
        return totalProfit;
    }

    public Integer getUnitsSold() {
        Integer totalUnits = 0;
        for (Transaction transactions : mTransactions) {
            totalUnits += transactions.getQuantity();
        }

        return totalUnits;
    }

    public Item mostProfitable() {
        HashMap<Item, Double> map = new HashMap<>();
        for (Transaction transactions : mTransactions) {
            //gets value in collection, but if null then returns default (gets back 0 as current gross if not in the map)
            String itemId = transactions.getItemId();
            Item item = mItems.get(itemId);

            //sets item to be current gross plus the rental fee to what I am looking at
            double currentGross = map.getOrDefault(transactions, 0.0);

            //tallies up rental sum plus the rental we are looking at
            map.put(item, currentGross + transactions.getTotal());
        }
        //contains all items rented and all fees ever charged in transaction history

        Item maxItem = null;
        double maxValue = 0;

        //Map.entry iterates over what we already have in hashmap
        for (Map.Entry<Item, Double> entry : map.entrySet()) { //hash table (collection of map entries)
            //iterating over entries in map, looking at value for each item
            if (entry.getValue() > maxValue) { //store entry if greater than max value, otherwise it is skipped over
                maxItem = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        //now maxValue contains largest value, and maxItem contains the item that eared that value
        return maxItem;
    }

    public Collection<Item> sortedItems() {
        ItemIdComparator idComparator = new ItemIdComparator();

        List<Item> items = new ArrayList<>(mItems.values());

        Collections.sort(items, idComparator);

        return items;
    }

    public Employee sortedGross() {
        SortedMap<Employee, Double> map = new TreeMap<>();

        Employee highestPaid = null;
        double highestSalary = 0;

        for (Employee employee : mEmployee.values()) {
            if (employee.getSalary() > highestSalary) {
                highestSalary = employee.getSalary();
                highestPaid = employee;
            }
            map.put(employee, employee.getSalary());
        }
        return highestPaid;
    }

    public Collection<Employee> getSortedEmployees() {
        SortedMap<Employee, Double> map = new TreeMap<>();

        for (Employee employee : mEmployee.values()) {
            map.put(employee, employee.getSalary());
        }
        Collection<Employee> rv = map.keySet();
        return rv;
    }

}

