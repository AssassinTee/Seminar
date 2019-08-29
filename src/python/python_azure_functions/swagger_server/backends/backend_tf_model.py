from keras.models import model_from_json

class KerasBackend:
    def __init__(self):
        # load json and create model
        json_file = open('models/model_kaggle_cooking.json', 'r')
        loaded_model_json = json_file.read()
        json_file.close()
        self.model = model_from_json(loaded_model_json)
        
        # load weights into new model
        self.model.load_weights("models/model_kaggle_cooking.h5")
        print("Loaded model from disk")
        
    def predict(self, X):
        """
        gets ndarray
        returns ndarray
        """
        return self.model.predict(X)
        
