import mxnet as mx

class MxnetBackend:
    def __init__(self):
        print("MXNet Version:", mx.__version__)
        self.sym, self.arg_params, self.aux_params = mx.model.load_checkpoint(prefix='models/kc_mxnet', epoch=0)
        self.mod = mx.mod.Module(symbol=self.sym, 
                    data_names=['/dense_1_input1'], 
                    context=mx.cpu(), 
                    label_names=None)
        self.mod.bind(for_training=False, 
                data_shapes=[('/dense_1_input1', (512, 3010))], 
                label_shapes=self.mod._label_shapes)
        self.mod.set_params(self.arg_params, self.aux_params, allow_missing=True)
        
    def predict(self, X):
        """
        gets ndarray
        returns ndarray
        """
        res = self.mod.predict(X).asnumpy()
        return res
