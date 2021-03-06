package network.minter.blockchain.samples.candidates;

import android.util.Log;

import java.util.List;

import javax.annotation.Nonnull;

import network.minter.blockchain.MinterBlockChainApi;
import network.minter.blockchain.models.BCResult;
import network.minter.blockchain.models.CandidateStatus;
import network.minter.blockchain.repo.BlockChainCandidateRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class get {

    public static void main(String[] args) {
        MinterBlockChainApi.initialize("https://minter-node-1.testnet.minter.network:8841");

        BlockChainCandidateRepository repository = MinterBlockChainApi.getInstance().candidate();

        // Get candidate by Public Key
        Call<BCResult<List<CandidateStatus>>> request = repository.getBlockCandidates(100);

        request.enqueue(new Callback<BCResult<List<CandidateStatus>>>() {
            @Override
            public void onResponse(@Nonnull Call<BCResult<List<CandidateStatus>>> call, @Nonnull Response<BCResult<List<CandidateStatus>>> response) {
                BCResult<List<CandidateStatus>> body = response.body();
                if (body.isOk()) {
                    // do something with body.result
                } else {
                    Log.d("MinterError", body.error.getMessage());
                }
            }

            @Override
            public void onFailure(@Nonnull Call<BCResult<List<CandidateStatus>>> call, @Nonnull Throwable t) {
                // handle error
            }
        });
    }
}
