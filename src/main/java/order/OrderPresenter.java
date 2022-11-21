package order;

public class OrderPresenter implements OrderOutputBoundary {

    private OrderViewModel viewModel;

    public OrderPresenter(OrderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(OrderResponseModel response) {

    }
}
