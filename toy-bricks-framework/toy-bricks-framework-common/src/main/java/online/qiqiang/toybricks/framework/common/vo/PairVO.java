package online.qiqiang.toybricks.framework.common.vo;

import lombok.*;

/**
 * @author quince
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PairVO<L,R> {
    private L left;
    private R right;
}
