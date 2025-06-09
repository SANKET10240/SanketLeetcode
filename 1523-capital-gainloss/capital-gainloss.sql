# Write your MySQL query statement below
# Write your MySQL query statement below
select stock_name,
    sum(case when operation = 'Buy' then -price when operation = 'Sell' 
    then price else 0 end) as capital_gain_loss
from stocks
group by stock_name;