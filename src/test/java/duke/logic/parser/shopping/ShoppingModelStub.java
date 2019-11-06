package duke.logic.parser.shopping;

import duke.commons.core.index.Index;
import duke.logic.command.order.SortOrderCommand;
import duke.logic.command.product.SortProductCommand;
import duke.model.Model;
import duke.model.ReadOnlyBakingHome;
import duke.model.commons.Item;
import duke.model.inventory.Ingredient;
import duke.model.order.Order;
import duke.model.product.Product;
import duke.model.sale.Sale;
import duke.model.shortcut.Shortcut;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ShoppingModelStub implements Model {

    private List<Item<Ingredient>> shoppingList;

    public ShoppingModelStub() {
        shoppingList = new ArrayList<>();
    }

    @Override
    public ObservableList<Item<Ingredient>> getFilteredShoppingList() {
        return null;
    }

    @Override
    public void updateFilteredShoppingList(Predicate<Item<Ingredient>> predicate) {

    }

    @Override
    public void addShoppingList(Item<Ingredient> toAdd) {
        shoppingList.add(toAdd);
    }

    @Override
    public boolean hasShoppingList(Item<Ingredient> ingredientItem) {
        return shoppingList.contains(ingredientItem);
    }

    @Override
    public void deleteShoppingList(Item<Ingredient> toDelete) {
        shoppingList.remove(toDelete);
    }

    @Override
    public void setShoppingList(Item<Ingredient> toEdit, Item<Ingredient> edited) {
        shoppingList.set(shoppingList.indexOf(toEdit), edited);
    }

    @Override
    public void setShoppingList(List<Item<Ingredient>> replacement) {

    }

    @Override
    public void clearShoppingList(List<Item<Ingredient>> emptyList) {
        shoppingList.clear();
    }

    @Override
    public Double computeTotalCost(ArrayList<Item<Ingredient>> ingredientList) {
        Double totalCost = 0.0;
        for (Item<Ingredient> item : ingredientList) {
            Index index = Index.fromZeroBased(shoppingList.indexOf(item));
            totalCost += shoppingList.get(index.getZeroBased()).getItem().getUnitPrice()
                    * item.getQuantity().getNumber();
        }
        return totalCost;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public boolean canRedo() {
        return false;
    }

    @Override
    public String undo() {
        return null;
    }

    @Override
    public String redo() {
        return null;
    }

    @Override
    public void commit(String commitMessage) {

    }

    @Override
    public void setVersionControl(Boolean isEnabled) {

    }

    @Override
    public ReadOnlyBakingHome getBakingHome() {
        return null;
    }

    @Override
    public void setBakingHome(ReadOnlyBakingHome bakingHome) {

    }

    @Override
    public boolean hasOrder(Order order) {
        return false;
    }

    @Override
    public void deleteOrder(Order target) {

    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void setOrder(Order target, Order editedOrder) {

    }

    @Override
    public void setOrder(Index index, Order order) {

    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return null;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {

    }

    @Override
    public void sortOrders(SortOrderCommand.SortCriteria criteria, boolean isReversed) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void setProduct(Product originalProduct, Product editedProduct) {

    }

    @Override
    public boolean hasProduct(Product product) {
        return false;
    }

    @Override
    public void sortProducts(SortProductCommand.Category category, boolean isReverse) {

    }

    @Override
    public ObservableList<Product> getFilteredProductList() {
        return null;
    }

    @Override
    public void updateFilteredProductList(Predicate<Product> predicate) {

    }

    @Override
    public void getProductWithKeyword(String keyword) {

    }

    @Override
    public boolean hasSale(Sale sale) {
        return false;
    }

    @Override
    public void deleteSale(Sale target) {

    }

    @Override
    public void addSale(Sale sale) {

    }

    @Override
    public void setSale(Sale target, Sale editedSale) {

    }

    @Override
    public void setSale(Index index, Sale sale) {

    }

    @Override
    public ObservableList<Sale> getFilteredSaleList() {
        return null;
    }

    @Override
    public void updateFilteredSaleList(Predicate<Sale> predicate) {

    }

    @Override
    public void addSaleFromOrder(Order order) {

    }

    @Override
    public void addSaleFromShopping(Double totalCost, ArrayList<Item<Ingredient>> toBuyList) {

    }

    @Override
    public ObservableList<Item<Ingredient>> getFilteredInventoryList() {
        return null;
    }

    @Override
    public void updateFilteredInventoryList(Predicate<Item<Ingredient>> predicate) {

    }

    @Override
    public void addInventory(Item<Ingredient> inventory) {

    }

    @Override
    public boolean hasInventory(Item<Ingredient> inventory) {
        return false;
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean deductIngredient(Ingredient ingredient, double amount) {
        return false;
    }

    @Override
    public void deleteInventory(Item<Ingredient> inventory) {

    }

    @Override
    public void setInventory(Item<Ingredient> toEdit, Item<Ingredient> edited) {

    }

    @Override
    public void setInventory(List<Item<Ingredient>> replacement) {

    }

    @Override
    public void clearInventory(List<Item<Ingredient>> emptyList) {

    }

    @Override
    public void setShortcut(Shortcut shortcut) {

    }

    @Override
    public void removeShortcut(Shortcut shortcut) {

    }

    @Override
    public boolean hasShortcut(Shortcut shortcut) {
        return false;
    }

    @Override
    public List<Shortcut> getShortcutList() {
        return null;
    }
}
