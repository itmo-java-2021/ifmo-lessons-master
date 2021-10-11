package com.ifmo.lesson2;

public class LuckyTickets {
    /*
    В городе N проезд в трамвае осуществляется по бумажным отрывным билетам. Каждую
    неделю трамвайное депо заказывает в местной типографии рулон билетов с номерами от
    000001 до 999999. «Счастливым» считается билетик у которого сумма первых трёх цифр
    номера равна сумме последних трёх цифр, как, например, в билетах с номерами 003102 или
    567576. Трамвайное депо решило подарить сувенир обладателю каждого счастливого билета
    и теперь раздумывает, как много сувениров потребуется. С помощью программы подсчитайте
    сколько счастливых билетов в одном рулоне?
     */
    public static void main(String[] args) {

        System.out.println(luckyTickets());
    }

    public static int luckyTickets() {
        int count = 0;
        for (int i = 1; i < 1000000; i++) {
            String str = String.format("%6s", Integer.toString(i)).replace(' ', '0');
            int sum1 = Character.getNumericValue(str.charAt(0)) + Character.getNumericValue(str.charAt(1)) + Character.getNumericValue(str.charAt(2));
            int sum2 = Character.getNumericValue(str.charAt(3)) + Character.getNumericValue(str.charAt(4)) + Character.getNumericValue(str.charAt(5));
            if (sum1 == sum2){
                count++;
            }
        }
        return count;
    }
}
