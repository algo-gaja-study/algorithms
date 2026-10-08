class DynamicArray {

    private int[] data;
    private int size;

    public DynamicArray(int capacity) {
        data = new int[capacity];
        size=0;
    }

    public int get(int i) {
        return data[i];
    }

    public void set(int i, int n) {
        data[i]=n;
    }

    public void pushback(int n) {
        if (size == data.length) {
            resize();
        }
        data[size]=n;
        size+=1;
    }

    public int popback() {
        int popNum = data[size-1];
        size-=1;
        return popNum;
    }

    private void resize() {
        int[] newData = new int[size*2];
        for(int i=0;i<size;i++){
            newData[i]=data[i];
        }
        data = newData;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;

    }
}
