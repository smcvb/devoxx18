package com.devoxx.conference.querymodel;

import com.devoxx.conference.coreapi.FindAllWalletsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query")
public class WalletQueryController {

    private final QueryGateway queryGateway;

    public WalletQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping("/wallets")
    public CompletableFuture<List<WalletView>> wallets() {
        return queryGateway.query(new FindAllWalletsQuery(), ResponseTypes.multipleInstancesOf(WalletView.class));
    }
}
