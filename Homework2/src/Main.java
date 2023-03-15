public class Main {

    // задание полей
    static float apartmentPrice = 14_000; // астрономического телескопа
    static int account = 1_000; // счёт пользователя
    static float wage = 2_500; // заработная плата в месяц
    static int percentFree = 100; // доля заработной платы на любые траты
    static float percentBank = 5; // годовая процентная ставка за кредит
    static float[] monthlyPayments = new float[120]; // создание массива ежемесячных платежей на 10 лет


    // метод подсчёта стоимости квартиры с учётом первоначального взноса
    static private float apartmentPriceWithContribution() {
        return apartmentPrice - account; // возврат подсчитанного значения
    }

    // метод подсчёта ежемесячных трат на кредит (зар.плата, процент своб.трат)
    static public float mortgageCosts(float amount, int percent) {
        return (amount*percent)/100;
    }

    // метод подсчёта времени выплаты кредита (сумма долга, сумма платежа, годовой процент)
    // и заполнение массива monthlyPayments[] ежемесячными платежами
    static public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12; // подсчёт ежемесячного процента банка за кредит
        int count = 0; // счётчик месяцев выплаты кредита

        // алгоритм расчёта кредита
        while (total>0) {
            count++; // добавление нового месяца платежа
            total = (total + (total*percentBankMonth)/100) - mortgageCosts; // вычисление долга с учётом выплаты и процента
            // заполнение массива ежемесячными платежами за кредит
            if(total > mortgageCosts) { // если сумма долга больше ежемесячного платежа, то
                monthlyPayments[count-1] = mortgageCosts; // в массив добавляется целый платёж
            } else { // иначе
                monthlyPayments[count-1] = total; // в массив добавляется платёж равный остатку долга
            }
        }

        return count;
    }


    public static void main(String[] args) {
        

        
        // 1) вывод количества месяцев выплаты кредита
        System.out.println("Кредит будет выплачиваться " + countMonth(apartmentPriceWithContribution(),
                mortgageCosts(wage, percentFree),percentBank) + " месяцев");
        // 2) подготовка выписки
        String monthlyPaymentsList = "";
        for(float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемесячных выплат по кредиту
        System.out.println("Первоначальный взнос " + account + " монет, ежемесячные выплаты: " + monthlyPaymentsList);
    }




}